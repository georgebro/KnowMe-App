package sample.databases;

import sample.databases.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * created by GGB 18/10/2020
 * Class to read from and write to DAATABASE
 */

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        // plagin for connect JAVA code to the MYSQL
        String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;

        Class.forName("com.mysql.jdbc.Driver");
         dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPassword);

         // return -> Correct way to connect to databases
         return dbConnection; //


        // WRITE to DB

        // READ from DB
    }
}
