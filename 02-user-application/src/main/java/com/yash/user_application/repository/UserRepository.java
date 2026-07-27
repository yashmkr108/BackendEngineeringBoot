package com.yash.user_application.repository;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.dto.user.UserSummary;
import com.yash.user_application.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRoleOrderByIdDesc(Role role);

    List<User> findByFirstNameAndRole(String first_name, Role role);

    List<User> findByFirstNameContainingIgnoreCaseOrderByIdAsc(String text);

    @Query("""
            Select u.email FROM User u
            """)
    List<String> getEmails();

    @Query("""
            SELECT u FROM User u WHERE u.email = :email
            """)
    Optional<User> getUserByEmail(@Param("email") String userEmail); // By @Param Annotation used for explicit binding
//    Optional<User> getUserByEmail(String email); // By Parameter Name

    // Need Specific fields
    @Query("""
                Select new com.yash.user_application.dto.user.UserSummary(
                            u.email,
                            u.firstName,
                            u.lastName
                ) 
                From User u
            """)
    List<UserSummary> getEmailFirstNameLastNameOfUsers();

    @Query("""
            Select Sum(u.id) From User u
            """)
    Long getIdSum();

    @Query("""
            Select Max(u.id) From User u
            """)
    Long getMaxId();

    @Query("""
            Select Min(u.id) From User u
            """)
    Long getMinId();

    @Query("""
            Select Avg(u.id) From User u
            """)
    Double getAvgId();

}