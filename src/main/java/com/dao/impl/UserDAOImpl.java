package com.dao.impl;

import com.dao.UserDAO;
import com.dto.request.CustomerLoginReq;
import com.dto.request.DriverRegistrationReq;
import com.dto.request.UserRegistrationReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.response.UserRegistrationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    @Override
    public GeneralResponse userRegistration(UserRegistrationReq userRegistrationReq) {
        GeneralResponse response = null;
        int isInserted = 0;
        int DriverIsInserted = 0;
        try {
            String INSERT_USER_SQL = "insert into userdetail(FirstName, LastName, EmailAddress, Address, MobileNumber, UserPassword, UserType)" +
                    " VALUES (?,?,?,?,?,?,?)";

            isInserted =
                    jdbcTemplate.update(INSERT_USER_SQL,
                            userRegistrationReq.getFirstName(),
                            userRegistrationReq.getLastName(),
                            userRegistrationReq.getEmailAddress(),
                            userRegistrationReq.getAddress(),
                            userRegistrationReq.getMobileNumber(),
                            passcodeEncrypt(passcodeEncrypt(userRegistrationReq.getUserPassword())),
                            userRegistrationReq.getUserType()
                            );

            if(isInserted == 1){
                    if (userRegistrationReq.getUserType() == 3){
                        Connection connection = null;
                        Statement statement = null;
                        ResultSet resultSet = null;
                        int userId=0;

                        String SELECT_LAST_INSERTED_ID = " SELECT MAX(UserDetailId) AS max_id from userdetail";
                        connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(SELECT_LAST_INSERTED_ID);
                        while (resultSet.next()){
                             userId = (resultSet.getInt("max_id"));
                        }

                        String INSERT_DRIVER_SQL = "insert into driver(UserDetailId, VehicleDetailId, BranchId)" +
                                " VALUES (?,?,?)";
                        DriverIsInserted =
                                jdbcTemplate.update(INSERT_DRIVER_SQL,
                                        userId,
                                        userRegistrationReq.getVehicleId(),
                                        userRegistrationReq.getBranchId()
                                );
                        if (DriverIsInserted == 1) {
                            response = GeneralResponse.generateResponse(
                                    getUserByEmail(userRegistrationReq.getEmailAddress()),
                                    1000,
                                    "Driver " +
                                            userRegistrationReq.getFirstName() + " - " + userRegistrationReq.getLastName() +
                                            "created successfully...!"
                            );
                        }
                    }else {
                        response = GeneralResponse.generateResponse(
                                getUserByEmail(userRegistrationReq.getEmailAddress()),
                                1000,
                                "Customer "+
                                        userRegistrationReq.getFirstName() +" - "+userRegistrationReq.getLastName()+
                                        " created successfully...!"
                        );
                        }
            }else {
                response = GeneralResponse.generateResponse(null,1001,"Failed to register the Customer...!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            response = GeneralResponse.generateResponse(null,1001,"Failed to register the Customer...!");
        }
        return response;
    }

    public UserRegistrationRes getUserByEmail(String emailAddress) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        UserRegistrationRes registrationRes = null;
        try {
            String SELECT_USER_BY_EMAIL = "select * from userdetail where EmailAddress = '"+emailAddress+"'";
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_USER_BY_EMAIL);
            while (resultSet.next()){
                registrationRes = new UserRegistrationRes();
                registrationRes.setUserId(resultSet.getInt("UserDetailId"));
                registrationRes.setUserType(resultSet.getInt("UserType"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
        return registrationRes;
    }

    public String passcodeEncrypt(String passcode) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(passcode.getBytes("UTF-8"));
        byte[] unhash = outputStream.toByteArray();
        byte[] digestHash = digest.digest(unhash);
        return bytesToHex(digestHash);
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    public int userLogin(CustomerLoginReq customerLoginReq) {
        int loginStatus = 0;
        try{
            String QUERY = "select count(*) from userdetail " +
                    "where EmailAddress = '"+customerLoginReq.getUsername()+"' and UserPassword = '"+customerLoginReq.getPasscode()+"'";
            loginStatus = jdbcTemplate.queryForObject(QUERY,Integer.class);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return loginStatus;
    }

    @Override
    @Transactional
    public CommonResponse driverRegistration(DriverRegistrationReq driverRegistrationReq) {
        CommonResponse commonResponse = new CommonResponse();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            String CREATE_DRIVER = "{call insert_update_driver(?,?,?,?,?,?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(CREATE_DRIVER);

            callableStatement.setObject(1, driverRegistrationReq.getFirstName(), Types.VARCHAR);
            callableStatement.setObject(2, driverRegistrationReq.getLastName(), Types.VARCHAR);
            callableStatement.setObject(3, driverRegistrationReq.getEmailAddress(), Types.VARCHAR);
            callableStatement.setObject(4, driverRegistrationReq.getAddress(), Types.VARCHAR);
            callableStatement.setObject(5, driverRegistrationReq.getMobileNumber(), Types.VARCHAR);
            String pwd = passcodeEncrypt(passcodeEncrypt(driverRegistrationReq.getUserPassword()));
            callableStatement.setObject(6, pwd, Types.VARCHAR);
            callableStatement.setObject(7, driverRegistrationReq.getVehicleDetailId(), Types.INTEGER);
            callableStatement.setObject(8, driverRegistrationReq.getBranchId(), Types.INTEGER);
            callableStatement.registerOutParameter(9, Types.BOOLEAN);
            callableStatement.registerOutParameter(10, Types.INTEGER);
            callableStatement.registerOutParameter(11, Types.VARCHAR);
            callableStatement.executeUpdate();

            commonResponse.setMessage(callableStatement.getString(11));
            commonResponse.setStatusCode(callableStatement.getInt(10));
            commonResponse.setRes(callableStatement.getBoolean(9));
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
        }
        return commonResponse;
    }

}
