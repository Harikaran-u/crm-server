package com.project.crm.utils;

import com.project.crm.model.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ValidationUtils {
    public static boolean isValidEmail(String email){
        String regexEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(regexEmail);
    }
    public static boolean isValidPhoneNumber(Long phone){
        String regexPhone = "^[6-9]\\d{9}$";
        return phone.toString().matches(regexPhone);
    }
    public static boolean isValidPinCode(Integer pincode){
        String regexPinCode = "^\\d{6}$";
        return pincode.toString().matches(regexPinCode);
    }

    public static boolean updateAddress(Address oldAddress, Address presentAddress){
        if(presentAddress.getStreetName() != null && !presentAddress.getStreetName().isEmpty()){
            oldAddress.setStreetName(presentAddress.getStreetName());
        }
        if(presentAddress.getCity() != null && !presentAddress.getCity().isEmpty()){
            oldAddress.setCity(presentAddress.getCity());
        }
        if(presentAddress.getDistrict() != null && !presentAddress.getDistrict().isEmpty()){
            oldAddress.setDistrict(presentAddress.getDistrict());
        }
        if(presentAddress.getState() != null && !presentAddress.getState().isEmpty()){
            oldAddress.setState(presentAddress.getState());
        }
        if(presentAddress.getDoorNo() != null){
            oldAddress.setDoorNo(presentAddress.getDoorNo());
        }
        if(presentAddress.getPincode() != null){
            if(!ValidationUtils.isValidPinCode(presentAddress.getPincode())){
                return false;
            }
            oldAddress.setPincode(presentAddress.getPincode());
        }
        return true;
    }

    public static Double roundToTwoDecimal(Double value) {
        if (value == null) return null;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static LocalDate getStartOfFinancialYear() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        if (now.getMonthValue() < 4) {
            year--;
        }
        return LocalDate.of(year, 4, 1);
    }

    public static LocalDate getEndOfFinancialYear() {
        LocalDate start = getStartOfFinancialYear();
        return start.plusYears(1).minusDays(1);
    }

    public static String getQuarter(LocalDate date) {
        int month = date.getMonthValue();
        if (month >= 4 && month <= 6) return "Q1";
        if (month >= 7 && month <= 9) return "Q2";
        if (month >= 10) return "Q3";
        return "Q4";
    }
}
