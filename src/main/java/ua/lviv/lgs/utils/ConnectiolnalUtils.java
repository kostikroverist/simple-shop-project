package ua.lviv.lgs.utils;

import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectiolnalUtils {
    private static String URL =  "jdbc:mysql://localhost:3306/i_shop?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
    private static String USER_NAME="root";
    private static String USER_PASSWORD ="987zxcvbnm";




    public  static Connection openConnection() throws InstantiationException,IllegalAccessException,ClassNotFoundException, SQLException {
       // DOMConfigurator.configure("LoggerConfig.xml");
        Class.forName ("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection (URL, USER_NAME, USER_PASSWORD);

    }
}