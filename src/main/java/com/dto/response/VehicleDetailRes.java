package com.dto.response;

import com.dto.request.VehicleDetailReq;

public class VehicleDetailRes{
    private int vehicleDetailId;
    private String brandName;
    private String modelName;
    private String vehicleCategoryName;


    public int getVehicleDetailId() {
        return vehicleDetailId;
    }

    public void setVehicleDetailId(int vehicleDetailId) {
        this.vehicleDetailId = vehicleDetailId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVehicleCategoryName() {
        return vehicleCategoryName;
    }

    public void setVehicleCategoryName(String vehicleCategoryName) {
        this.vehicleCategoryName = vehicleCategoryName;
    }
}
