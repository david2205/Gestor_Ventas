
package com.uptc.servlets;

import com.google.gson.Gson;
import com.uptc.modelo.management;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class control_venta extends HttpServlet {
    management mana;

    public control_venta() {
        mana = new management();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String path = getServletContext().getRealPath("/")+"resources/files/";
            
            mana.leerClientesFl(path, "clientes.json");
            mana.leerProductosFl(path, "productos.json");
            mana.leerVentasFl(path, "ventas.json");
            String option = request.getParameter("option");
            switch (option) {
                case "0":
                    out.println(new Gson().toJson(mana.getListaVenta()));
                    break;
                  
                case "1":
                    if(mana.borrarProducto(request.getParameter("id"))){
                           out.println("Borrado");
                        }else{
                           out.println("Error");
                        }
                        
                    break;
                case "2":
                    System.out.println("Codigo A buscar " + request.getParameter("id") );
                    out.println(mana.ConsultarVenta(request.getParameter("id")));
                        
                break;    
                        
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(control_venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String path = getServletContext().getRealPath("/")+"resources/files/";
            mana.leerVentasFl(path, "ventas.json");
            mana.leerClientesFl(path, "clientes.json");
            mana.leerProductosFl(path, "productos.json");
            String Codigo  = request.getParameter("codigo");
            String id_cliente  = request.getParameter("id_cliente");
            String cod_producto  = request.getParameter("cod_producto");
            int cantidad  =  Integer.parseInt(request.getParameter("cantidad"));
            
        try ( PrintWriter out = response.getWriter()) {
            
            out.println(mana.AgregarVenta(Codigo, id_cliente, cod_producto, cantidad));
        } catch (Exception ex) {
            Logger.getLogger(control_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
