
package com.uptc.persistencia;

import com.uptc.modelo.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class clienteDAO {
     private conexion_DB conexion;
    Connection conn;
    PreparedStatement Ps;
    ResultSet Rs;
    cliente cliente = new cliente();

    public clienteDAO() {
        conexion = new conexion_DB();
    }
    
    public ResultSet leer() throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_clientes");
        }
        return null;
        
    }
    public ResultSet buscar(String id) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeQuery("select * from tb_clientes"
                    + "WHERE codigo = '"+id+"'");
        }
        return null;
        
    }
    public int crear(cliente cliente ) throws SQLException{
        
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            System.out.println("insert into tb_clientes "
                    + "values('"+cliente.getId()+"','"+cliente.getNombres()+"','"+cliente.getApellidos()+"','"+cliente.getContacto()+"',"+cliente.getEdad()+")");
            return statement.executeUpdate("insert into tb_clientes "
                    + "values('"+cliente.getId()+"','"+cliente.getNombres()+"','"+cliente.getApellidos()+"','"+cliente.getContacto()+"',"+cliente.getEdad()+")");
            
        }

        return 0;
        
    }
    
   
    
    public int borrar(String id) throws SQLException{
       
        if (conexion.isConnect()) {
            Statement statement = conexion.getConnection().createStatement();
            return statement.executeUpdate("delete from tb_clientes where id='"+id+"'");
            
        }
        return 0;
        
    }
}
