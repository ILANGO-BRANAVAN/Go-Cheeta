package com.business.impl;

import com.business.TripBusiness;
import com.dao.TripDAO;
import com.dto.request.*;
import com.dto.response.GeneralResponse;
import com.dto.response.LocationRes;
import com.dto.response.TripRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripBusinessImpl implements TripBusiness {
    @Autowired
    TripDAO tripDAO;

    @Override
    public GeneralResponse createNewTrip(CreateTripReq createTripReq) {
        return tripDAO.createNewTrip(createTripReq);
    }

    @Override
    public GeneralResponse editTrip(EditTripReq editTripReq) {
        return tripDAO.editTrip(editTripReq);
    }

    @Override
    public GeneralResponse assignDriverToTrip(AssignDriverReq assignDriverReq) {
        return tripDAO.assignDriverToTrip(assignDriverReq);
    }

    @Override
    public GeneralResponse cancelTrip(CancelTripReq cancelTripReq) {
        return tripDAO.cancelTrip(cancelTripReq);
    }

    @Override
    public List<TripRes> getTripList() {
        return tripDAO.getTripList();
    }

    @Override
    public TripRes getTripDetail(TripDetailReq tripDetailReq) {
        return tripDAO.getTripDetail(tripDetailReq);
    }

    @Override
    public List<LocationRes> getLocationList() {
        return tripDAO.getLocationList();
    }

    @Override
    public List<LocationRes> getLocationListByCity(GetLocationByCityReq getLocationByCityReq) {
        return tripDAO.getLocationListByCity(getLocationByCityReq);
    }
}
