package sample.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 * created by GGB
 * 19/10/2020
 * Class read from DB and write to DB
 */


public class DatabaseHandler extends Configs {

    Connection dbConnection;


    // connecting JAVA code to the MYSQL Server and DB
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?" +
                "autoReconnect=true&useSSL=false" +
                "&useUnicode=true&" + "useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC";
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
            } catch (ClassNotFoundException e) {
        throw new IllegalStateException("Cannot find the driver in the classpath!", e);
    }
        System.out.println(" SERVER -> STARTED!!!");
        return dbConnection;
    }



    //SQL request to the DB ,in order to put the data to the DB
    //SQL request UPPER-CASE naming conventions
    //All our fields and values, that insert into the database.
    //"VALUES(?,?,?,?,?,?)" -> adding values to the database
    // prSt -> executes the request
    // FUNCTION THAT UPDATE DATA TO DB and recorder new user to the Database
    public void signUpUserRecorder(User user){
        String insert  = " INSERT INTO " + Const.USER_TABLE + " ( " +
                Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_LOCATION + "," + Const.USERS_GENDER + " ) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,user.getFirstName());
            prSt.setString(2,user.getLastName());
            prSt.setString(3,user.getUserName());
            prSt.setString(4,user.getPassword());
            prSt.setString(5,user.getLocation());
            prSt.setString(6,user.getGender());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // the user enters his nickname and password, if they are correct,
    // then get the correct values from the database.
    // resSet -> returb user data from the DB in the form of a array.
    // FUNCTION THAT QUERY DATA FROM DB
    public ResultSet getUserData(User user){
        ResultSet resSet = null;
        String select = " SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUserName());
            prSt.setString(2,user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
