package com.business;

import com.dto.request.*;
import com.dto.response.GeneralResponse;
import com.dto.response.LocationRes;
import com.dto.response.TripRes;

import java.util.List;

public interface TripBusiness {
    /**
     * createNewTrip
     * @param createTripReq
     * @return
     */
    GeneralResponse createNewTrip(CreateTripReq createTripReq);

    /**
     * editTrip
     * @param editTripReq
     * @return
     */
    GeneralResponse editTrip(EditTripReq editTripReq);

    /**
     * assignDriverToTrip
     * @param assignDriverReq
     * @return
     */
    GeneralResponse assignDriverToTrip(AssignDriverReq assignDriverReq);

    /**
     * cancelTrip
     * @param cancelTripReq
     * @return
     */
    GeneralResponse cancelTrip(CancelTripReq cancelTripReq);

    /**
     * getTripList
     * @return
     */
    List<TripRes> getTripList();

    /**
     * getTripDetail
     * @param tripDetailReq
     * @return
     */
    TripRes getTripDetail(TripDetailReq tripDetailReq);

    /**
     * getLocationList
     * @return
     */
    List<LocationRes> getLocationList();

    /**
     * getLocationListByCity
     * @param getLocationByCityReq
     * @return
     */
    List<LocationRes> getLocationListByCity(GetLocationByCityReq getLocationByCityReq);
}
