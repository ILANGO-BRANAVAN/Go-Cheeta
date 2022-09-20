package com.controller.imp;

import com.business.TripBusiness;
import com.controller.TripController;
import com.dto.request.*;
import com.dto.response.GeneralResponse;
import com.dto.response.LocationRes;
import com.dto.response.TripRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TripControllerImpl implements TripController {

    @Autowired
    TripBusiness tripBusiness;

    @Override
    @PostMapping("/trip/create")
    public GeneralResponse createNewTrip(@RequestBody CreateTripReq createTripReq) {
        return tripBusiness.createNewTrip(createTripReq);
    }

    @Override
    @PostMapping("/trip/edit")
    public GeneralResponse editTrip(@RequestBody EditTripReq editTripReq) {
        return tripBusiness.editTrip(editTripReq);
    }

    @Override
    @PostMapping("/trip/assign/driver")
    public GeneralResponse assignDriverToTrip(@RequestBody AssignDriverReq assignDriverReq) {
        return tripBusiness.assignDriverToTrip(assignDriverReq);
    }

    @Override
    @PostMapping("/trip/cancel")
    public GeneralResponse cancelTrip(@RequestBody CancelTripReq cancelTripReq) {
        return tripBusiness.cancelTrip(cancelTripReq);
    }

    @Override
    @PostMapping("/trip/list")
    public GeneralResponse getTripList() {
        List<TripRes> list = tripBusiness.getTripList();
        return GeneralResponse.generateResponse(list,1000,"Success");
    }

    @Override
    @PostMapping("/trip/detail")
    public GeneralResponse getTripDetail(@RequestBody TripDetailReq tripDetailReq) {
        TripRes tripDetail = tripBusiness.getTripDetail(tripDetailReq);
        return GeneralResponse.generateResponse(tripDetail,1000,"Success");
    }

    @Override
    @PostMapping("/trip/location/list")
    public GeneralResponse getLocationList() {
        List<LocationRes> list = tripBusiness.getLocationList();
        return GeneralResponse.generateResponse(list,1000,"Success");
    }

    @Override
    @PostMapping("/trip/location/by/city")
    public GeneralResponse getLocationListByCity(@RequestBody GetLocationByCityReq getLocationByCityReq) {
        List<LocationRes> list = tripBusiness.getLocationListByCity(getLocationByCityReq);
        return GeneralResponse.generateResponse(list,1000,"Success");
    }
}
