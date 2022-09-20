package com.business;

import com.dto.request.CustomerLoginReq;
import com.dto.request.DriverRegistrationReq;
import com.dto.request.UserRegistrationReq;
import com.dto.response.GeneralResponse;
import com.dto.response.UserDetailRes;
import com.dto.response.VehicleDetailListRes;

import java.util.List;

public interface UserBusiness {
    /**
     * userRegistration
     * @param userRegistrationReq
     * @return
     */
    GeneralResponse userRegistration(UserRegistrationReq userRegistrationReq);

    /**
     * userLogin
     * @param customerLoginReq
     * @return
     */
    GeneralResponse userLogin(CustomerLoginReq customerLoginReq);

    /**
     * driverRegistration
     * @param driverRegistrationReq
     * @return
     */
    GeneralResponse driverRegistration(DriverRegistrationReq driverRegistrationReq);

   List<UserDetailRes> getUserDetails();
}
