package com.hms.hospitalmanagementsystem.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Response.SmsResponse;

public interface UserLoginRepository extends  GenericRepo {

    List<Map<String,Object>> findByUserName(String username) throws BadRequestException;
    List<Map<String,Object>> getByEmailOrEmail(String email,String mobileNo);
    Map<String,Object> getSmsProperties(String templeteId);

    public void saveSmsLog(SmsResponse body, String templateId, String emailId);

    
}
