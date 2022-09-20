package com.business.impl;

import com.business.VehicleBusiness;
import com.dao.VehicleDAO;
import com.dto.request.GetVehicleDetailReq;
import com.dto.request.VehicleCategoryReq;
import com.dto.request.VehicleDetailReq;
import com.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleBusinessImpl implements VehicleBusiness {

    @Autowired
    VehicleDAO vehicleDAO;

    @Override
    public CommonResponse createVehicleCategory(VehicleCategoryReq vehicleCategoryReq) {
        return vehicleDAO.createVehicleCategory(vehicleCategoryReq);
    }

    @Override
    public List<VehicleCategoriesRes> getVehicleCategories() {
        return vehicleDAO.getVehicleCategories();
    }

    @Override
    public CommonResponse createVehicleDetail(VehicleDetailReq vehicleDetailReq) {
        return vehicleDAO.createVehicleDetail(vehicleDetailReq);
    }

    @Override
    public GeneralResponse getVehicleDetail(GetVehicleDetailReq getVehicleDetailReq) {
        VehicleDetailRes vehicleDetailRes = vehicleDAO.getVehicleDetail(getVehicleDetailReq);
        if(vehicleDetailRes != null){
            return GeneralResponse.generateResponse(vehicleDetailRes,1000,"Success");
        }else {
            return GeneralResponse.generateResponse(null,1001,"Failed...!");
        }
    }

    @Override
    public List<VehicleDetailListRes> getVehicleDetails() {
        return vehicleDAO.getVehicleDetails();
    }
}
