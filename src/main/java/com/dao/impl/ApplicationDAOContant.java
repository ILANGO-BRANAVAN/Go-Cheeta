package com.dao.impl;

public class ApplicationDAOContant {
    interface IVehicle{
        String INSERT_VEHICLE_CATEGORY = "insert into vehiclecategory(VehicleCategoryName) VALUES (?)";
        String GET_VEHICLE_CATEGORIES = "select VehicleCategoryId,VehicleCategoryName from vehiclecategory";

        String INSERT_VEHICLE_DETAIL = "insert into vehicledetail(BrandName, ModelName, VehicleCategoryId) VALUES (?,?,?)";

        String GET_VEHICLE_DETAIL = "select vd.VehicleDetailid,vd.BrandName,vd.ModelName,v.VehicleCategoryName from vehicledetail vd inner join vehiclecategory v on vd.VehicleCategoryId = v.VehicleCategoryId where VehicleDetailId = ";

        String GET_VEHICLE_DETAIL_LIST = "select vd.VehicleDetailId,vd.BrandName,vd.ModelName,v.VehicleCategoryName from vehicledetail vd inner join vehiclecategory v on vd.VehicleCategoryId = v.VehicleCategoryId";
    }

    interface ITrip{
        String INSERT_TRIP = "insert into trip(SourceLocationId, DestinationLocationId, TravelDateTime, TripFare, TripStatus, BranchId, UserDetailId) VALUES (?,?,?,?,?,?,?)";

        String EDIT_TRIP = "";

        String CANCEL_TRIP = "";

        String TRIP_LIST = "";

        String TRIP_DETAIL = "";
        String GET_LOCATIONS = "select LocationId,StreetAddress,City from location";
        String GET_LOCATIONS_BY_CITY = "select LocationId, StreetAddress from location where City = ";
    }
}
