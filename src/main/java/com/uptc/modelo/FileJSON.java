
package com.uptc.modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class FileJSON {
    
    private String path;
    private String name;

    public FileJSON() {
        
    }
    
    public void openFile( String path, String name ){
        System.err.println(path+name);
        
        this.path = path;
        this.name = name;
    }
    
    public ArrayList<cliente> getClientes() throws FileNotFoundException{
        Gson gson = new Gson();
        
        
        Type type = new TypeToken<ArrayList<cliente>>(){}.getType();
        ArrayList<cliente> out = gson.fromJson(new FileReader( path + name ), type );
        
        return out;
    }
    
    public ArrayList<producto> getProductos() throws FileNotFoundException{
        Gson gson = new Gson();
        
        Type type = new TypeToken<ArrayList<producto>>(){}.getType();
        ArrayList<producto> out = gson.fromJson(new FileReader( path + name ), type );
        
        return out;
    }
    public ArrayList<venta> getVentas() throws FileNotFoundException{
        Gson gson = new Gson();
        
        Type type = new TypeToken<ArrayList<venta>>(){}.getType();
        ArrayList<venta> out = gson.fromJson(new FileReader( path + name ), type );
        
        return out;
    }
      public boolean printCliente(ArrayList<cliente> listaClientes)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path+name))) {
        Gson gson = new Gson();
        String json = gson.toJson(listaClientes);
        System.out.print(path+name);
        System.out.print(json);
        bw.write(json);
        System.out.println("Fichero creado");
        } catch (Exception ex) {
           return false;
        }
        return false;
    }
    public boolean printProducto(ArrayList<producto> listaProdcutos)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path+name))) {
        Gson gson = new Gson();
        String json = gson.toJson(listaProdcutos);
        System.out.print(path+name);
        System.out.print(json);
        bw.write(json);
        System.out.println("Fichero creado");
        } catch (Exception ex) {
           return false;
        }
        return false;
    }
    public boolean printVenta(ArrayList<venta> listaVentas)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path+"ventas.json"))) {
        Gson gson = new Gson();
        String json = gson.toJson(listaVentas);
        System.out.print(path+name);
        System.out.print(json);
        bw.write(json);
        System.out.println("Fichero creado");
        } catch (Exception ex) {
           return false;
        }
        return false;
    }
}
