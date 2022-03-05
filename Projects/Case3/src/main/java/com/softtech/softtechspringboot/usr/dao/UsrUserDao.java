package com.softtech.softtechspringboot.usr.dao;

import com.softtech.softtechspringboot.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser,Long> {

    Optional<UsrUser> findByUsername(String username);
    Optional<UsrUser> findByEmail(String email);
    Optional<UsrUser> findByPhoneNumber(String phoneNumber);


}
