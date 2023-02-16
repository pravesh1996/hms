package com.hms.hospitalmanagementsystem.Service;

import java.io.UnsupportedEncodingException;

import com.hms.hospitalmanagementsystem.Exception.BadRequestException;

public interface UserLoginService extends GenericService {

    public void sendOtp(String mobileNo) throws BadRequestException, UnsupportedEncodingException;
    
}
