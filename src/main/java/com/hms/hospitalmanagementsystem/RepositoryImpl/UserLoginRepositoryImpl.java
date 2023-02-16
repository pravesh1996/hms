package com.hms.hospitalmanagementsystem.RepositoryImpl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Repository.UserLoginRepository;
import com.hms.hospitalmanagementsystem.Response.SmsResponse;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserLoginRepositoryImpl implements UserLoginRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> saveDetails(Map<String, Object> req, Principal user) throws BadRequestException {
        return null;
    }

    @Override
    public Map<String, Object> updateDetails(Map<String, Object> req, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> deleteDetails(Map<String, Object> req, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> getById(String uuid, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> getAll(Principal principal) {
    
        return jdbcTemplate.queryForList("select * from user_login");
    }

    @Override
    public Page<Map<String, Object>> getAllDetails(Pageable page, Principal user) throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> addvanceSearch(Map<String, Object> req, Pageable page, Principal user)
            throws BadRequestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> findByUserName(String username) throws BadRequestException {

      
     if(username!=null){
       try{
        Map<String,Object> _map=new HashMap<>();
        _map.put("username", username);
        List<Map<String, Object>>  userDetails=jdbcTemplate.queryForList(" SELECT ul.`username`,ul.`mobileNo`,ul.`password`,ur.`authority` FROM `user_login` ul LEFT JOIN user_roles ur ON ul.`username`=ur.`username` where ul.username=?",_map.get("username").toString() );
        return userDetails;
       }catch(UsernameNotFoundException e){
        System.out.println("user name not found"+e.getMessage());
       }      
       
     }else{
        throw new BadRequestException("Invalid Request");
     }
       
     return null;
    }

    @Override
    public List<Map<String, Object>> getByEmailOrEmail(String email, String mobileNo) {

        String query="SELECT ul.`username` AS userName,ul.`mobileNo` AS mobileNo FROM `user_login` ul WHERE ul.`username`=? OR mobileNo= ?";
      
        return jdbcTemplate.queryForList(query, email,mobileNo);
    }

    @Override
    public Map<String, Object> getSmsProperties(String templeteId) {
       
        return jdbcTemplate.queryForMap("SELECT base_url,ukey,credittype,language,senderid,templateid,filetype,isactive FROM sms_properties where isactive=true and templateid=?", templeteId);
    }

    @Override
    public void saveSmsLog(SmsResponse body, String templateId, String emailId) {
        
        
        String query = "INSERT INTO `sms_log`(`http_response_code`,`message`,`phone`,`response_code`,`status`,`delivery_time`,`sms_properties_id`,`created_by`,`last_modified_by`)\r\n"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        Object args[] = { body.getHttpresponseCode(), body.getMessage(), body.getPhone(), body.getResponseCode(),
                body.getStatus(), body.getDeliveryTime(), templateId, emailId, emailId };
        jdbcTemplate.update(query, args);
        
    }
    
}
