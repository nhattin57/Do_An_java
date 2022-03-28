/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan_java;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
/**
 *
 * @author admin
 */
public class ConnecToDatabase {
     public Connection conn;
     public   Statement myStmt=null;
     public   ResultSet myRs=null;
    public Connection getConnection() throws SQLException{
        if(conn==null){
            conn= (Connection) DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLSV");
            return conn;
        }
        return conn;
    }
}
