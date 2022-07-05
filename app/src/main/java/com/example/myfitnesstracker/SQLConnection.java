package com.example.myfitnesstracker;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

//highly experimental work in progress

public class SQLConnection {
    String connectionURL =
            "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                    + "database=test;"
                    + "user=yourusername@yourserver;"
                    + "password=yourpassword;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
    void test(){
        Connection con = DriverManager.getConnection(connectionURL);
    }
}
