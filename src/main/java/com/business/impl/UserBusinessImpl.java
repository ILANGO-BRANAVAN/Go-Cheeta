package com.business.impl;

import com.business.UserBusiness;
import com.dao.UserDAO;
import com.dto.request.CustomerLoginReq;
import com.dto.request.DriverRegistrationReq;
import com.dto.request.UserRegistrationReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.response.UserDetailRes;
import com.dto.response.UserRegistrationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    UserDAO userDAO;
    @Override
    public GeneralResponse userRegistration(UserRegistrationReq userRegistrationReq) {
        if(validateFieldOnUserReg(userRegistrationReq))
            return userDAO.userRegistration(userRegistrationReq);
        else
            return new GeneralResponse(null,1001,"Please update the mandatory values...!");
    }

    private boolean validateFieldOnUserReg(UserRegistrationReq userRegistrationReq) {
        boolean bool = false;
        if(userRegistrationReq.getFirstName() != null && !userRegistrationReq.getFirstName().equals("")){
            bool = true;
        }
        if(userRegistrationReq.getLastName() != null && !userRegistrationReq.getLastName().equals("")){
            bool = true;
        }
        if(userRegistrationReq.getEmailAddress() != null && !userRegistrationReq.getEmailAddress().equals("")){
            bool = true;
        }
        if(userRegistrationReq.getMobileNumber() != null && !userRegistrationReq.getMobileNumber().equals("")){
            bool = true;
        }
        if(userRegistrationReq.getAddress() != null && !userRegistrationReq.getAddress().equals("")){
            bool = true;
        }
        if(userRegistrationReq.getUserPassword() != null && !userRegistrationReq.getUserPassword().equals("")){
            bool = true;
        }
        return bool;
    }

    @Override
    public GeneralResponse userLogin(CustomerLoginReq customerLoginReq) {
        GeneralResponse generalResponse = null;
        try {
            String encryptedPassword = userDAO.passcodeEncrypt(customerLoginReq.getPasscode());
            String encryptedPassword2 = userDAO.passcodeEncrypt(encryptedPassword);

            customerLoginReq.setPasscode(encryptedPassword2);

            int loginStatus = userDAO.userLogin(customerLoginReq);

            if(loginStatus == 1){
                UserRegistrationRes registrationRes  = userDAO.getUserByEmail(customerLoginReq.getUsername());
                generalResponse = new GeneralResponse(registrationRes,1000,"Successfully logged in...!");
            }else {
                generalResponse = new GeneralResponse(null,1003,"Login Failed, Please try again..!");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return generalResponse;
    }

    @Override
    public GeneralResponse driverRegistration(DriverRegistrationReq driverRegistrationReq) {
        GeneralResponse generalResponse = new GeneralResponse();
        CommonResponse commonResponse = userDAO.driverRegistration(driverRegistrationReq);

        if(commonResponse.getRes()){
            UserRegistrationRes registrationRes  = userDAO.getUserByEmail(driverRegistrationReq.getEmailAddress());
            generalResponse.setData(registrationRes);
            generalResponse.setStatusCode(commonResponse.getStatusCode());
            generalResponse.setMessage(commonResponse.getMessage());
        }else {
            generalResponse.setData(null);
            generalResponse.setStatusCode(commonResponse.getStatusCode());
            generalResponse.setMessage(commonResponse.getMessage());
        }
        return generalResponse;
    }

    @Override
    public List<UserDetailRes> getUserDetails() {
        return userDAO.getUserDetails();
    }
}
