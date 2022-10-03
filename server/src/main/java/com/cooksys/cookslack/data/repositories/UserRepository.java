package com.cooksys.cookslack.data.repositories;

import com.cooksys.cookslack.data.model.entities.Company;
import com.cooksys.cookslack.data.model.entities.Team;
import com.cooksys.cookslack.data.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // VALIDATION QUERIES
    Boolean existsUserByCredentialsUsernameAndDeletedFalse(String username);

    Boolean existsUserByEmailAndDeletedFalse(String email);

    ////////



    // RETRIEVAL QUERIES

    /**
     * Finds any <em>non-deleted</em> <code>User</code> by <code>username</code>
     * @param username
     * @return
     */
    Optional<User> findByCredentialsUsernameAndDeletedFalse(String username);

    /**
     * Finds any <code>User</code> by <code>username</code>, <em>including deleted <code>User</code>s</em>
     * @param username
     * @return <code>Optional User</code>
     */
    Optional<User> findByCredentialsUsername(String username);

    /**
     * Finds all active users associated with a given <code>Company</code>
     * @param company
     * @return a list of <em>active</em> users
     */
    List<User> findAllByCompanyAndDeletedFalseAndActiveTrue(Company company);

    /**
     * Finds all active users associated with a given <code>Team</code>
     * @param team
     * @return a list of <em>active</em> users
     */
    List<User> findAllByTeamAndDeletedFalseAndActiveTrue(Team team);

    /**
     * Finds all users associated with a given <code>Company</code> <em>including</em> inactive users
     * @param company
     * @return a list of users
     */
    List<User> findAllByCompanyAndDeletedFalse(Company company);

    /**
     * Finds all users associated with a given <code>Team</code> <em>including</em> inactive users
     * @param team
     * @return a list of users
     */
    List<User> findAllByTeamAndDeletedFalse(Team team);

    ////////

}
