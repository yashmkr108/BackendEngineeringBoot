package com.yash.user_application.service;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.dto.user.*;
import com.yash.user_application.enums.Role;
import com.yash.user_application.exceptions.UserNotFoundException;
import com.yash.user_application.mapper.user.MultipleUserMapper;
import com.yash.user_application.mapper.user.UserMapper;
import com.yash.user_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // adding user repository dependency
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MultipleUserMapper multipleUserMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, MultipleUserMapper multipleUserMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.multipleUserMapper = multipleUserMapper;
    }

    public List<UserResponse> getAllUsers() {
        List<User> rawUsers = userRepository.findAll();
        return multipleUserMapper.toAllUsersResponse(rawUsers);
    }

    public UserResponse createUser(CreateUserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.STUDENT);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    public UserResponse getUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        User user = optionalUser.get();

        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        System.out.println(user);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.deleteById(id);
    }

    public UserResponse getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        User user = optionalUser.get();
        return userMapper.toResponse(user);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<UserResponse> getRoleList(Role role) {
        List<User> rawUsers = userRepository.findByRoleOrderByIdDesc(role);
        return multipleUserMapper.toAllUsersResponse(rawUsers);
    }

    public List<UserResponse> getFirstNameAndRoleList(String first_name, Role role) {
        List<User> rawUsers = userRepository.findByFirstNameAndRole(first_name, role);
        return multipleUserMapper.toAllUsersResponse(rawUsers);
    }

    public List<String> getEmails() {
        return userRepository.getEmails();
    }

    public List<UserSummary> getEmailFirstNameLastNameOfUsers() {
        return userRepository.getEmailFirstNameLastNameOfUsers();
    }

    public UserStatisticResponse getUserStats() {
        Long count = userRepository.count(); // build in method returns the count of the user table
        Long idSum = userRepository.getIdSum();
        Long idMax = userRepository.getMaxId();
        Long idMin = userRepository.getMinId();
        Double idAvg = userRepository.getAvgId();

        return new UserStatisticResponse(count, idSum, idMax, idMin, idAvg);
    }

    public List<RoleCountResponse> countUsersByRole() {
        return userRepository.countUsersByRole();
    }

    public List<RoleCountResponse> findPopularRoles() {
        return userRepository.findPopularRoles();
    }

    public List<Role> getRoles() {
        return userRepository.getRoles();
    }

    public List<UserCategoryResponse> getCategories() {
        return userRepository.getCategories();
    }

    @Transactional
    public Integer deactivateAllStudents() {
        return userRepository.deactivateAllStudents(Role.STUDENT);
    }

    public List<UserResponse> getActiveUsers() {
        List<User> rawUsers = userRepository.getActiveUsers();
        return multipleUserMapper.toAllUsersResponse(rawUsers);
    }

    public List<UserResponse> getInactiveUsers() {
        List<User> rawUsers = userRepository.getInactiveUsers();
        return multipleUserMapper.toAllUsersResponse(rawUsers);
    }

    @Transactional
    public Integer deactivateUser(Long id){
        return userRepository.updateActiveStatus(id,false);
    }

    @Transactional
    public Integer activateUser(Long id){
        return userRepository.updateActiveStatus(id,true);
    }
}
