package testFramework.utils;

import java.sql.*;

public class DBConnections {

    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_URL_DEV = "jdbc:sqlserver://omdsqv051\\dev:63106;databaseName=RMM_BSSP_OneFM_App;integratedSecurity=true";
    private static final String DATABASE_URL_QA = "jdbc:sqlserver://omnsqv051\\qa:54452;databaseName=RMM_BSSP_OneFM_App;integratedSecurity=true";

    public Connection getDEVConnection() throws Exception {
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(DATABASE_URL_DEV);
            System.out.println("[Info] Database Connection String is: " + DATABASE_URL_DEV);
            return conn;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to get the database connection string - " + ex.getMessage());
            return null;
        }
    }

    public Connection getQAConnection() throws Exception {
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(DATABASE_URL_QA);
            System.out.println("[Info] Database Connection String is: " + DATABASE_URL_QA);
            return conn;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to get the database connection string - " + ex.getMessage());
            return null;
        }
    }

    public static boolean close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to close the result set - " + ex.getMessage());
            return false;
        }
    }

    public static boolean close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to close the statement - " + ex.getMessage());
            return false;
        }
    }

    public static boolean close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to close the database connection - " + ex.getMessage());
            return false;
        }
    }
}
