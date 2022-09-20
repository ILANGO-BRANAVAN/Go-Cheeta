package com.controller;

import com.dto.request.*;
import com.dto.response.GeneralResponse;

public interface TripController {
    /**
     * createNewTrip
     * @param createTripReq
     * @return
     */
    public GeneralResponse createNewTrip(CreateTripReq createTripReq);

    /**
     * editTrip
     * @param editTripReq
     * @return
     */
    public GeneralResponse editTrip(EditTripReq editTripReq);

    /**
     * assignDriverToTrip
     * @param assignDriverReq
     * @return
     */
    public GeneralResponse assignDriverToTrip(AssignDriverReq assignDriverReq);

    /**
     * cancelTrip
     * @param cancelTripReq
     * @return
     */
    public GeneralResponse cancelTrip(CancelTripReq cancelTripReq);

    /**
     * getTripList
     * @return
     */
    public GeneralResponse getTripList();

    /**
     * getTripDetail
     * @param tripDetailReq
     * @return
     */
    public GeneralResponse getTripDetail(TripDetailReq tripDetailReq);

    /**
     * getLocationList
     * @return
     */
    public GeneralResponse getLocationList();

    /**
     * getLocationListByCity
     * @param getLocationByCityReq
     * @return
     */
    public GeneralResponse getLocationListByCity(GetLocationByCityReq getLocationByCityReq);
}
