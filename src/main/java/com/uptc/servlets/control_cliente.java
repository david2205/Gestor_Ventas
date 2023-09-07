
package com.uptc.servlets;

import com.google.gson.Gson;
import com.uptc.modelo.cliente;
import com.uptc.modelo.management;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class control_cliente extends HttpServlet {

        management mana;

    public control_cliente() {
        mana = new management();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = getServletContext().getRealPath("/")+"resources/files/";
            mana.leerClientesFl(path, "clientes.json");
          
            String option = request.getParameter("option");
            switch (option) {
                case "0":
                    out.println(new Gson().toJson(mana.getListaClientes()));
                    break;
                  
                case "1":
                    if(mana.borrarCliente(request.getParameter("id"))){
                           out.println("Cliente Borrado");
                        }else{
                           out.println("Error el Id Ingresado no existe");
                        }
                        
                    break;
                case "2":
                    out.println(mana.ConsultarClinte(request.getParameter("id")));
                        
                break;    
                        
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(control_cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String id = request.getParameter("id");
            String Nombres = request.getParameter("nombres");
            String Apellidos = request.getParameter("apellidos");
            String Contacto = request.getParameter("contacto");
            int Edad = Integer.parseInt(request.getParameter("edad"));
            
        try ( PrintWriter out = response.getWriter()) {
            String path = getServletContext().getRealPath("/")+"resources/files/";
            mana.leerClientesFl(path, "clientes.json");
            if (mana.AgregarCliente(id, Nombres, Apellidos, Contacto, Edad)) {
                out.println(new Gson().toJson("response"));
            } else {
                out.println(new Gson().toJson(false));
            }
        } catch (SQLException ex) {
            Logger.getLogger(control_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
