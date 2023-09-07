/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uptc.modelo;

/**
 *
 * @author alejandro
 */
public class producto {
    
    String Codigo;
    String Nombre;
    String Fecha;
    double Valor;
    
    public producto() {
    }

    public producto(String Codigo, String Nombre, String Fecha, double Valor) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Fecha = Fecha;
        this.Valor = Valor;
    }
    
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }
    
}
