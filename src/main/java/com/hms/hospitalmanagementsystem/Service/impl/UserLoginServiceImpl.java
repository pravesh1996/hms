package com.hms.hospitalmanagementsystem.Service.impl;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Exception.UnauthoriseException;
import com.hms.hospitalmanagementsystem.Repository.UserLoginRepository;
import com.hms.hospitalmanagementsystem.RepositoryImpl.UserLoginRepositoryImpl;
import com.hms.hospitalmanagementsystem.Service.UserLoginService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserLoginService{

    private final UserLoginRepositoryImpl loginRepository;

    @Override
    public Map<String, Object> saveDetails(Map<String, Object> request, Principal user)
            throws BadRequestException, Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> updateDetails(Map<String, Object> request, Principal user)
            throws BadRequestException, UnauthoriseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> deleteDetails(String request, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> getById(String id, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> getAll(Principal principal) {
      return loginRepository.getAll(principal);
    
      
    }

    @Override
    public Page<Map<String, Object>> getAllDetails(Pageable page, Principal user)
            throws BadRequestException, UnauthoriseException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> addvanceSearch(Map<String, Object> request, Pageable page, Principal user)
            throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

 
   

  

    
}
