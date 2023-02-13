package com.hms.hospitalmanagementsystem.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Exception.UnauthoriseException;

public interface GenericService {

    Map<String, Object> saveDetails(Map<String, Object> request,Principal user) throws BadRequestException, Exception;
	Map<String, Object> updateDetails(Map<String, Object> request,Principal user) throws BadRequestException, UnauthoriseException;
	Map<String, Object> deleteDetails(String request,Principal user) throws BadRequestException;
	Map<String, Object> getById(String id,Principal user) throws BadRequestException;
    List<Map<String,Object>> getAll(Principal principal);
	Page<Map<String, Object>> getAllDetails(Pageable page,Principal user) throws BadRequestException, UnauthoriseException;
	Map<String, Object> addvanceSearch(Map<String, Object> request,Pageable page,Principal user) throws BadRequestException;
    
}
