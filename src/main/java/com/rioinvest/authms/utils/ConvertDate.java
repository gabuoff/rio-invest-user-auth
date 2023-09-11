package com.rioinvest.authms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    
    public static Date converDate(String date){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
             return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid dateOfBirth format. Please use 'dd/MM/yyyy' format.", e);
        }
    }
}
