
package com.uptc.modelo;


public class venta {
    String Codigo;
    String id_cliente;
    String cod_producto;
    int Cantidad;
    double total;

    public venta(String Codigo, String id_cliente, String cod_producto, int Cantidad, double total) {
        this.Codigo = Codigo;
        this.id_cliente = id_cliente;
        this.cod_producto = cod_producto;
        this.Cantidad = Cantidad;
        this.total = total;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public venta() {
    }
    
}
