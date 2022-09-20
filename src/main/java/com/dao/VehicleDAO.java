package com.dao;

import com.dto.request.GetVehicleDetailReq;
import com.dto.request.VehicleCategoryReq;
import com.dto.request.VehicleDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.VehicleCategoriesRes;
import com.dto.response.VehicleDetailListRes;
import com.dto.response.VehicleDetailRes;

import java.util.List;

public interface VehicleDAO {
    CommonResponse createVehicleCategory(VehicleCategoryReq vehicleCategoryReq);

    List<VehicleCategoriesRes> getVehicleCategories();

    CommonResponse createVehicleDetail(VehicleDetailReq vehicleDetailReq);

    VehicleDetailRes getVehicleDetail(GetVehicleDetailReq getVehicleDetailReq);

    List<VehicleDetailListRes> getVehicleDetails();
}
