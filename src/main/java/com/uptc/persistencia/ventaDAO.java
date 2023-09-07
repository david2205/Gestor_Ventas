
package com.uptc.persistencia;

import com.uptc.modelo.venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ventaDAO {
     private conexion_DB conexion;
    Connection conn;
    PreparedStatement Ps;
    ResultSet Rs;
    venta venta = new venta();

    public ventaDAO() {
        conexion = new conexion_DB();
    }
    
    public ResultSet leer() throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_ventas");
        }
        return null;
        
    }
    public ResultSet buscar(String codigo) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_ventas "
                    + "WHERE codigo = '"+codigo+"'");
        }
        return null;
        
    }
    public int crear(venta venta) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeUpdate("insert into tb_ventas"
                    + "values('"+venta.getCodigo()+"','"+venta.getId_cliente()+"','"+venta.getCod_producto()+"',"+venta.getCantidad()+"',"+venta.getTotal()+")");
            
        }

        return 0;
        
    }
    
   
    
    public int borrar(String codigo) throws SQLException{
       
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeUpdate("DELETE FROM tb_ventas "
                    + "WHERE codigo='"+codigo+"'");
            
        }
        return 0;
        
    }
}
