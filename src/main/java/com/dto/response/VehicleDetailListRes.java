package com.dto.response;

public class VehicleDetailListRes {
    private int vehicleId;
    private String brandName;
    private String modelName;
    private String vehicleCategoryName;

    public int getVehicleId() { return vehicleId; }

    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

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
