/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.VisitanteDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhonjaider1000
 */
@WebServlet(name = "VisitantesServlet", urlPatterns = {"/guardarVisitante"})
public class VisitantesServlet extends HttpServlet {

    private VisitanteDelegado delegadoVisitante;

    @Override
    public void init() throws ServletException {
        super.init();
        delegadoVisitante = VisitanteDelegado.getInstancia();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Respuesta respuesta = new Respuesta();
        try {
            String url = request.getServletPath();
            EDireccion direccion = EDireccion.getDireccion(url);
            switch(direccion){
                case GUARDAR_VISITANTE:
                    respuesta = guardarVisitante(request);
                    break;
            }
        } catch (Exception ex) {
            System.err.println("Error desde el servlet: "+ex);
        } finally {
            String json = new Gson().toJson(respuesta);
            out.print(json);
            out.close();
        }
    }

    private Respuesta guardarVisitante(HttpServletRequest request) throws IOException{
        String ipVisitante = request.getParameter("ipVisitante");
        return delegadoVisitante.guardarVisitante(ipVisitante);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
