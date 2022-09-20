package com.dao;

import com.dto.request.CustomerLoginReq;
import com.dto.request.DriverRegistrationReq;
import com.dto.request.UserRegistrationReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.response.UserRegistrationRes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface UserDAO {
    /**
     * userRegistration
     * @param userRegistrationReq
     * @return
     */
    GeneralResponse userRegistration(UserRegistrationReq userRegistrationReq);

    public UserRegistrationRes getUserByEmail(String emailAddress);

    public String passcodeEncrypt(String passcode) throws NoSuchAlgorithmException, IOException;

    public int userLogin(CustomerLoginReq customerLoginReq);

    CommonResponse driverRegistration(DriverRegistrationReq driverRegistrationReq);

}
// need to create dao