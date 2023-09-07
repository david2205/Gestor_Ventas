
package com.uptc.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion_DB {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection connection; 
    private final String URL = "jdbc:mysql://localhost:3306/bd_trabajo";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public boolean isConnect( ){
        boolean state = false;
        try {
            Class.forName( DRIVER ).newInstance( );
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            state = true;
        } catch (ClassNotFoundException ex) {
            
        } catch (InstantiationException ex) {
            
        } catch (IllegalAccessException ex) {
            
        } catch (SQLException ex) {
            
        }
        
        return state;
    }

    public Connection getConnection() {
        return connection;
    }
}
