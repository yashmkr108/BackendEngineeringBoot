package com.yash.user_application.controller;

import com.yash.user_application.dto.user.*;
import com.yash.user_application.enums.Role;
import com.yash.user_application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // prefix
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping  // /->route
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {

        return userService.updateUser(id, request);
    }


    @GetMapping(params = "email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/exists")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean existsByEmail(@RequestParam String email) {
        return userService.existsByEmail(email);
    }

    @GetMapping(params = "role")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getRoleList(@RequestParam Role role) {
        return userService.getRoleList(role);
    }

    @GetMapping(params = {"role", "first_name"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getFirstNameAndRoleList(@RequestParam Role role, @RequestParam String first_name) {
        return userService.getFirstNameAndRoleList(first_name, role);
    }

    @GetMapping("/emails")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<String> getEmails() {
        return userService.getEmails();
    }

    // This return the selected fields from the user entity
    @GetMapping("/selectedFields")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserSummary> getEmailFirstNameLastNameOfUsers() {
        return userService.getEmailFirstNameLastNameOfUsers();
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserStatisticResponse getUserStats() {
        return userService.getUserStats();
    }

    @GetMapping("/roleCount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<RoleCountResponse> countUsersByRole() {
        return userService.countUsersByRole();
    }

    @GetMapping("/popularRole")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<RoleCountResponse> findPopularRoles() {
        return userService.findPopularRoles();
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Role> getRoles(){
        return userService.getRoles();
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserCategoryResponse> getCategories(){
        return userService.getCategories();
    }

    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer deactivateAllStudents(){
        return userService.deactivateAllStudents();
    }

    @GetMapping("/active")
    public List<UserResponse> getActiveUsers(){
        return userService.getActiveUsers();
    }

    @GetMapping("/inactive")
    public List<UserResponse> getInactiveUsers(){
        return userService.getInactiveUsers();
    }

    @PatchMapping("/deactivate/{id}")
    public Integer deactivateUser(@PathVariable Long id){
        return userService.deactivateUser(id);
    }
    @PatchMapping("/activate/{id}")
    public Integer activateUser(@PathVariable Long id){
        return userService.activateUser(id);
    }
}
