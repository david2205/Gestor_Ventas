

package com.uptc.servlets;

import com.google.gson.Gson;
import com.uptc.modelo.management;
import com.uptc.modelo.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class control_producto extends HttpServlet {

    management mana;

    public control_producto() {
        mana = new management();
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = getServletContext().getRealPath("/")+"resources/files/";
            mana.leerProductosFl(path, "productos.json");
          
            String option = request.getParameter("option");
            switch (option) {
                case "0":
                    out.println(new Gson().toJson(mana.getListaProductos()));
                    break;
                  
                case "1":
                    if(mana.borrarProducto(request.getParameter("id"))){
                           out.println("Borrado");
                        }else{
                           out.println("Error");
                        }
                        
                    break;
                case "2":
                    out.println(mana.ConsultarProducto(request.getParameter("id")));
                        
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
            Logger.getLogger(control_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            String Codigo  = request.getParameter("codigo");
            String Nombre  = request.getParameter("nombre");
            String Fecha  = request.getParameter("fecha");
            double Valor  =  Double.parseDouble(request.getParameter("valor"));
            
        try ( PrintWriter out = response.getWriter()) {
            
            if (mana.AgregarProducto(Codigo, Nombre, Fecha, Valor)) {
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
