package com.hms.hospitalmanagementsystem.Response;

import lombok.Data;

@Data
public class SmsResponse {

    int httpresponseCode;
    String guid;
    String message;
    String phone;
    long responseCode;
    String status;
    String deliveryTime;
    
}
