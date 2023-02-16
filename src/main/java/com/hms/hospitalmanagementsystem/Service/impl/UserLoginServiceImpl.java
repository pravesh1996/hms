package com.hms.hospitalmanagementsystem.Service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.hms.hospitalmanagementsystem.Exception.BadRequestException;
import com.hms.hospitalmanagementsystem.Exception.UnauthoriseException;
import com.hms.hospitalmanagementsystem.Repository.UserLoginRepository;
import com.hms.hospitalmanagementsystem.RepositoryImpl.UserLoginRepositoryImpl;
import com.hms.hospitalmanagementsystem.Response.SmsResponse;
import com.hms.hospitalmanagementsystem.Service.UserLoginService;
import com.hms.hospitalmanagementsystem.Util.GeneralUtil;
import com.hms.hospitalmanagementsystem.Util.GlobalMessage;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserLoginService{

    private final UserLoginRepository loginRepository;

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

    @Override
    public void sendOtp(String mobileNo) throws BadRequestException, UnsupportedEncodingException {

        String otp="";

        List<Map<String, Object>>  listData=loginRepository.getByEmailOrEmail(null, mobileNo);
        if(listData==null || listData.size()==0){
            throw new BadRequestException(GlobalMessage.mobileNoNotExist);

        }

        Map<String,Object> smsPrps=loginRepository.getSmsProperties("2");
        
        List<Map<String, Object>> userData=loginRepository.findByUserName(listData.get(0).get("userName").toString());

        String baseUrl = smsPrps.get("base_url").toString();
        String ukey = smsPrps.get("ukey").toString();
        String language = smsPrps.get("LANGUAGE").toString();
        String credittype = smsPrps.get("credittype").toString();
        String senderid = smsPrps.get("senderid").toString();
        String templateid = smsPrps.get("templateid").toString();
        String filetype = smsPrps.get("filetype").toString();

        
        otp=GeneralUtil.genOtp(5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        otp = URLEncoder.encode(otp, "UTF-8");

        MultiValueMap<String, Object> mmap = new LinkedMultiValueMap<String, Object>();
        Map<String, Integer> params = new HashMap<>();
        params.put("otp", Integer.parseInt(otp));
        
        String otpMap="{ \"otp\":\""+otp+"\"}";

        mmap.add("templateName", ukey);
        mmap.add("phone", mobileNo);
        mmap.add("type", credittype);
        mmap.add("application", senderid);
        mmap.add("params", otpMap);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
                mmap, headers);

                System.out.println(request);

        RestTemplate restTemplate = new RestTemplate();
        

        try {
            ResponseEntity<SmsResponse> smsResponse = restTemplate.postForEntity(baseUrl, request,
                    SmsResponse.class);
                    loginRepository.saveSmsLog(smsResponse.getBody(), smsPrps.get("templateid").toString(),
                    listData.get(0).get("userName").toString());

                            System.out.println(smsResponse.getBody());

        } catch (HttpStatusCodeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

      
        // System.out.println(otp);
        // System.out.println(userData);

        
    }

 
   

  

    
}
