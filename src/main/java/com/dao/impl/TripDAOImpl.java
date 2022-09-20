package com.dao.impl;

import com.dao.TripDAO;
import com.dto.request.*;
import com.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripDAOImpl implements TripDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public GeneralResponse createNewTrip(CreateTripReq createTripReq) {
        GeneralResponse commonResponse = null;
        int isInserted = 0;
        try{
            isInserted = jdbcTemplate.update(ApplicationDAOContant.ITrip.INSERT_TRIP,
                    createTripReq.getSourceLocationId(),
                    createTripReq.getDestinationLocationId(),
                    createTripReq.getTravelDateTime(),
                    calculateCost(),
                    "confirm",
                    createTripReq.getBranchId(),
                    createTripReq.getUserDetailId()
            );

            if(isInserted == 1){
                commonResponse = new GeneralResponse(null,1000,"Success");
            }else {
                commonResponse = new GeneralResponse(null,1001,"Unable to create the trip, please try again...!");
            }
        }catch (Exception exception){
            exception.printStackTrace();
            commonResponse = new GeneralResponse(null,1001,"Unable to create the trip, please try again...!");
        }
        return commonResponse;
    }

    double calculateCost(){
        return 13.0;
    }

    @Override
    @Transactional
    public GeneralResponse editTrip(EditTripReq editTripReq) {
        return null;
    }

    @Override
    @Transactional
    public GeneralResponse assignDriverToTrip(AssignDriverReq assignDriverReq) {
        return null;
    }

    @Override
    @Transactional
    public GeneralResponse cancelTrip(CancelTripReq cancelTripReq) {
        return null;
    }

    @Override
    public List<TripRes> getTripList() {
        return null;
    }

    @Override
    public TripRes getTripDetail(TripDetailReq tripDetailReq) {
        return null;
    }

    @Override
    public List<LocationRes> getLocationList() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<LocationRes> list = new ArrayList<>();
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(ApplicationDAOContant.ITrip.GET_LOCATIONS);
            while (resultSet.next()){
                LocationRes res = new LocationRes();
                res.setLocationId(resultSet.getInt(1));
                res.setStreetAddress(resultSet.getString(2));
                res.setCity(resultSet.getString(2));

                list.add(res);
            }
        }catch (SQLException exception){

        }catch (Exception exception){

        }finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<LocationRes> getLocationListByCity(GetLocationByCityReq getLocationByCityReq) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<LocationRes> list = new ArrayList<>();
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            statement = connection.createStatement();
            String query = "select LocationId, StreetAddress from location where City = '"+getLocationByCityReq.getCity()+"'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                LocationRes res = new LocationRes();
                res.setLocationId(resultSet.getInt(1));
                res.setStreetAddress(resultSet.getString(2));

                list.add(res);
            }
        }catch (SQLException exception){

        }catch (Exception exception){

        }finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
