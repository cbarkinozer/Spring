package com.softtech.softtechspringboot.add.dao;

import com.softtech.softtechspringboot.add.entity.AddAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddAddressDao extends JpaRepository<AddAddress,Long> {

}
