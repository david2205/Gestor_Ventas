
package com.uptc.persistencia;


import com.uptc.modelo.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class productoDAO {
    private final conexion_DB conexion;
    Connection conn;
    PreparedStatement Ps;
    ResultSet Rs;

    public productoDAO() {
        conexion = new conexion_DB();
    }
    
    public ResultSet leer() throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_productos");
        }
        return null;
        
    }
    public ResultSet buscar(String codigo) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_productos "
                    + "WHERE codigo = '"+codigo+"'");
        }
        return null;
        
    }
    public int crear(producto producto) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeUpdate("insert into tb_productos "
                    + "values('"+producto.getCodigo()+"','"+producto.getNombre()+"','"+producto.getFecha()+"',"+producto.getValor()+")");
            
        }

        return 0;
        
    }
    
   
    
    public int borrar(String codigo) throws SQLException{
       
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeUpdate("DELETE FROM tb_productos "
                    + "WHERE codigo='"+codigo+"'");
            
        }
        return 0;
        
    }
}
