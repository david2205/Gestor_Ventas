package com.uptc.modelo;

import com.uptc.persistencia.clienteDAO;
import com.uptc.persistencia.productoDAO;
import com.uptc.persistencia.ventaDAO;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class management {
    ArrayList<cliente> listaClientes;
    ArrayList<producto> listaProductos;
    ArrayList<venta> listaVenta;
    clienteDAO clienteDAO; 
    productoDAO productoDAO;
    ventaDAO ventaDAO;
    FileJSON fileJson;
    
    public management() {
        listaClientes = new ArrayList<>();
        listaProductos = new ArrayList<>();
        listaVenta = new ArrayList<>();
        clienteDAO = new clienteDAO();
        productoDAO = new productoDAO();
        ventaDAO = new ventaDAO();
        fileJson = new FileJSON();
    }
    public void leerClientesFl(String path, String name) throws FileNotFoundException
    {
          fileJson.openFile(path, name);
        
          listaClientes = fileJson.getClientes();
          System.out.println("Cantidad leeia " + listaClientes.size() );
    }
      public void leerProductosFl(String path, String name) throws FileNotFoundException
    {
          fileJson.openFile(path, name);
        
          listaProductos = fileJson.getProductos();
    }
      public void leerVentasFl(String path, String name) throws FileNotFoundException
    {
          fileJson.openFile(path, name);
        
          listaVenta = fileJson.getVentas();
    }
      
      
      
  
    public void leerProductos() throws SQLException
    {
        ResultSet Rs = productoDAO.leer();
        listaProductos.clear();
        while(Rs.next())
        {
            listaProductos.add(
            new producto(Rs.getString("Codigo"), Rs.getString("Nombre"), Rs.getString("Fecha"),Double.parseDouble(Rs.getString("Valor")))
            );
        }
    }
    public void leerVentas() throws SQLException
    {
        ResultSet Rs = ventaDAO.leer();
        listaVenta.clear();
        while(Rs.next())
        {
            listaVenta.add(
                    new venta(Rs.getString("Codigo"), Rs.getString("id_cliente"),Rs.getString("cod_producto"),Integer.parseInt(Rs.getString("cantidad")) ,Double.parseDouble(Rs.getString("total")))
            );
        }
    }
    public cliente BuscarCliente(String id ){
        for ( cliente cliente : listaClientes ){
            if( cliente.getId().equals(id))return cliente;
        }
        return null;
    }
    public producto BuscarProducto(String code ){
        System.err.println("-----------------CODIGO "+code);
        for ( producto producto : listaProductos ){
            if( producto.getCodigo().equals(code))
            {
                System.err.println("-----------------LO ENCONTRAMOS");
                return producto;
            
            }
        }
        return null;
    }
    public venta BuscarVenta(String code ){
        for ( venta venta : listaVenta ){
            if( venta.getCodigo().equals(code))return venta;
        }
        return null;
    }
    public boolean AgregarCliente(String id, String Nombres, String Apellidos, String Contacto, int Edad) throws SQLException{
        if( BuscarCliente(id) == null ){
                cliente cliente = new cliente(id, Nombres, Apellidos, Contacto, Edad);
                listaClientes.add(cliente);
                fileJson.printCliente(listaClientes);
                return true;
            }
        return false;
    }
    public boolean AgregarProducto(String Codigo, String Nombre, String Fecha, double Valor) throws SQLException{
        if( BuscarProducto(Codigo) == null ){
                producto producto = new producto(Codigo, Nombre, Fecha, Valor);
                listaProductos.add(producto);
                fileJson.printProducto(listaProductos);
                return true;
            }
        return false;
    }
    public String AgregarVenta(String Codigo, String id_cliente, String cod_producto, int Cantidad) throws SQLException{
        if( BuscarVenta(Codigo) == null ){
            System.err.println("Codigo producto a buscar : "+cod_producto );
            producto producto = BuscarProducto(cod_producto);
            
            if(producto!=null){    
                if(BuscarCliente(id_cliente)!=null){    
                    venta venta = new venta(Codigo, id_cliente, cod_producto, Cantidad,Cantidad*producto.Valor );
                    listaVenta.add(venta);
                    fileJson.printVenta(listaVenta);
                    return "Venta Agregada con exito";
                }
            else return "El Id del Cliente No Existe";
            }
            else return "El Id del Producto No Existe";
        }
        return "No se ha podido agregar la venta";
    }
    public boolean borrarCliente( String id ) throws SQLException{
       
        if(listaClientes.remove(BuscarCliente(id))){
            fileJson.printCliente(listaClientes);
            return  true;
        };
        return false;
    }
    public boolean borrarProducto( String codigo ) throws SQLException{
       
       if(listaProductos.remove(BuscarProducto(codigo))){
            fileJson.printProducto(listaProductos);
            return  true;
        };
        return false;
    }
    
    public String ConsultarClinte(String id)
    {
        String Salida ="";
        cliente cliente = BuscarCliente(id);
        if( cliente != null)
        {
            Salida+="\n Nombre : "+cliente.Nombres;
            Salida+="\n Apellidos : "+cliente.Apellidos;
            Salida+="\n Contacto : "+cliente.Contacto;
            Salida+="\n Edad : "+cliente.Edad;
            return Salida;
        }
        return "Error el Id Ingresado no existe";
    }
    public String ConsultarProducto(String codigo)
    {
        String Salida ="";
        producto producto = BuscarProducto(codigo);
        if( producto != null)
        {
            Salida+="\n Nombre : "+producto.Nombre;
            Salida+="\n Descrp. : "+producto.Fecha;
            Salida+="\n Valor : "+producto.Valor;
            return Salida;
        }
        return "Error el Id Ingresado no existe";
    }
    public String ConsultarVenta(String codigo)
    {
         System.out.println("Codigo A buscar " + codigo );
        String Salida ="";
        venta venta = BuscarVenta(codigo);
        if( venta != null)
        {
            Salida+="\n Id Ciente : "+venta.id_cliente;
            Salida+="\n Codigo Producto : "+venta.cod_producto;
            Salida+="\n Cantidad : "+venta.getCantidad();
            Salida+="\n Total : "+venta.getTotal();
            return Salida;
        }
        return "Error el Id Ingresado no existe";
    }
    public ArrayList<cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(ArrayList<venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public clienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(clienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public productoDAO getProductoDAO() {
        return productoDAO;
    }

    public void setProductoDAO(productoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public ventaDAO getVentaDAO() {
        return ventaDAO;
    }

    public void setVentaDAO(ventaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }
    
    
}


