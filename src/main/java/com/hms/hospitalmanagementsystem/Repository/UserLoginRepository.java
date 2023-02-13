package com.hms.hospitalmanagementsystem.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hms.hospitalmanagementsystem.Exception.BadRequestException;

public interface UserLoginRepository extends  GenericRepo {

    List<Map<String,Object>> findByUserName(String username) throws BadRequestException;
    
}
