package com.controller.imp;

import com.business.VehicleBusiness;
import com.controller.VehicleController;
import com.dto.request.GetVehicleDetailReq;
import com.dto.request.VehicleCategoryReq;
import com.dto.request.VehicleDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class VehicleControllerImpl implements VehicleController {

    @Autowired
    VehicleBusiness vehicleBusiness;

    @Override
    @PostMapping("/vehicle/create/category")
    public GeneralResponse createVehicleCategory(@RequestBody VehicleCategoryReq vehicleCategoryReq) {
        CommonResponse commonResponse = vehicleBusiness.createVehicleCategory(vehicleCategoryReq);
        GeneralResponse response = new GeneralResponse();
        response.setData(null);
        response.setStatusCode(commonResponse.getStatusCode());
        response.setMessage(commonResponse.getMessage());
        return response;
    }

    @Override
    @PostMapping("/vehicle/get/categories")
    public GeneralResponse getVehicleCategories() {
        return GeneralResponse.generateResponse(vehicleBusiness.getVehicleCategories(),1000,"Success");
    }

    @Override
    @PostMapping("/vehicle/create/detail")
    public GeneralResponse createVehicleDetail(@RequestBody VehicleDetailReq vehicleDetailReq) {
        CommonResponse commonResponse = vehicleBusiness.createVehicleDetail(vehicleDetailReq);
        return GeneralResponse.generateResponse(null,commonResponse.getStatusCode(),commonResponse.getMessage());
    }

    @Override
    @PostMapping("/vehicle/get/detail")
    public GeneralResponse getVehicleDetail(@RequestBody GetVehicleDetailReq getVehicleDetailReq) {
        return vehicleBusiness.getVehicleDetail(getVehicleDetailReq);
    }

    @Override
    @PostMapping("/vehicle/get/detail/list")
    public GeneralResponse getVehicleDetails() {
        return GeneralResponse.generateResponse(vehicleBusiness.getVehicleDetails(),1000,"Success");
    }
}


