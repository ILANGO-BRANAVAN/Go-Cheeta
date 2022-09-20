package com.business;

import com.dto.request.GetVehicleDetailReq;
import com.dto.request.VehicleCategoryReq;
import com.dto.request.VehicleDetailReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.response.VehicleCategoriesRes;
import com.dto.response.VehicleDetailListRes;

import java.util.List;

public interface VehicleBusiness {
    CommonResponse createVehicleCategory(VehicleCategoryReq vehicleCategoryReq);

    List<VehicleCategoriesRes> getVehicleCategories();

    CommonResponse createVehicleDetail(VehicleDetailReq vehicleDetailReq);

    GeneralResponse getVehicleDetail(GetVehicleDetailReq getVehicleDetailReq);

    List<VehicleDetailListRes> getVehicleDetails();
}
