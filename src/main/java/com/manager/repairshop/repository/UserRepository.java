package com.manager.repairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email")
    public User getUserEmail(@Param("email") String email);

    // public Long countById(Integer id);

    // @Query("select u from User u where CONCAT(u.id,' ', u.email, ' ',
    // u.firstName,' ',"
    // + " u.lastName) LIKE %?1%")
    // public Page<User> findAll(String keyword, Pageable pageable);

    // @Query("update User u set u.enabled = ?2 where u.id = ?1")
    // @Modifying
    // public void updateEnabledStatus(Integer id, boolean enabled);

}
