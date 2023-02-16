package com.hms.hospitalmanagementsystem.Util;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtil {

   
        public static boolean isValidMobileNumber(String number) {
           String pattern = "^[6789]\\d{9}$";
           Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(number);
           return m.matches();
        }

        public static String genOtp(int len) {
		 
            String numbers = "0123456789";
            String values = numbers; 
            SecureRandom rndm_method = new SecureRandom(); 
      
            char[] password = new char[len]; 
      
            for (int i = 0; i < len; i++) 
            { 
                password[i] = 
                  values.charAt(rndm_method.nextInt(values.length())); 
      
            } 
            return String.valueOf(password); 
    }
    
}
