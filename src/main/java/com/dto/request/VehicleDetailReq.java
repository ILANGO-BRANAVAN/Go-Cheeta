package com.dto.request;

public class VehicleDetailReq {
    private String brandName;
    private String modelName;
    private int vehicleCategoryId;

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

    public int getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(int vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    @Override
    public String toString() {
        return "VehicleDetailReq{" +
                "brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", vehicleCategoryId=" + vehicleCategoryId +
                '}';
    }
}
