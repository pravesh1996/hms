package com.hms.hospitalmanagementsystem.Repository;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.hms.hospitalmanagementsystem.Exception.BadRequestException;

public interface GenericRepo {
    
	Map<String, Object> saveDetails(Map<String, Object> req,Principal user) throws BadRequestException;
	Map<String, Object> updateDetails(Map<String, Object> req,Principal user) throws BadRequestException;
	Map<String, Object> deleteDetails(Map<String, Object> req,Principal user) throws BadRequestException;
	Map<String, Object> getById(String uuid,Principal user) throws BadRequestException;
    List<Map<String,Object>> getAll(Principal principal);
	Page<Map<String, Object>> getAllDetails(Pageable page,Principal user) throws BadRequestException;
	Map<String, Object> addvanceSearch(Map<String, Object> req,Pageable page,Principal user) throws BadRequestException;
    
}
