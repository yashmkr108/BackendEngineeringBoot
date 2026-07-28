package com.yash.user_application.repository;

import com.yash.user_application.domain.user.User;
import com.yash.user_application.dto.user.RoleCountResponse;
import com.yash.user_application.dto.user.UserCategoryResponse;
import com.yash.user_application.dto.user.UserResponse;
import com.yash.user_application.dto.user.UserSummary;
import com.yash.user_application.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("""
            Select new com.yash.user_application.dto.user.RoleCountResponse(u.role,Count(u))
            From User u
            Group by u.role
            """)
    List<RoleCountResponse> countUsersByRole();

    @Query("""
            SELECT new com.yash.user_application.dto.user.RoleCountResponse(
                u.role,
                COUNT(u)
            )
            From User u
            Group By u.role
            Having Count(u) > 3
            """)
    List<RoleCountResponse> findPopularRoles();

    @Query("""
            Select DISTINCT(u.role) From User u
            """)
    List<Role> getRoles();

    @Query("""
            Select new com.yash.user_application.dto.user.UserCategoryResponse(
                        u.firstName,
                        CASE
                              WHEN u.role = com.yash.user_application.enums.Role.ADMIN THEN 'Staff'
                              WHEN u.role = com.yash.user_application.enums.Role.TEACHER THEN 'Staff'
                              ELSE 'Learner'
                        END
                        )
            From User u
            """)
    List<UserCategoryResponse> getCategories();

    @Modifying
    @Query("""
            Update User u Set u.isActive = false
            Where u.role = :role
            """
    )
    Integer deactivateAllStudents(Role role); // Updating query generally return how many rows are affected

    @Query("""
            Select u From User u Where u.isActive = true
            """)
    List<User> getActiveUsers();

    @Query("""
            Select u From User u Where u.isActive = false
            """)
    List<User> getInactiveUsers();

    @Modifying
    @Query("""
                UPDATE User u
                SET u.isActive = :active
                WHERE u.id = :id
            """)
    int updateActiveStatus(@Param("id") Long id,
                           @Param("active") boolean active);
}