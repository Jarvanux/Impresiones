/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.ContactoDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.entidades.Contacto;
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
 * @author hrey
 */
@WebServlet(name = "ContactoServlet", urlPatterns = {"/insertarmensaje", "/leermensaje", "/consultarporid"})
public class ContactoServlet extends HttpServlet {

    private ContactoDelegado delegadoContacto;

    @Override
    public void init() throws ServletException {
        super.init();
        delegadoContacto = ContactoDelegado.getInstancia();
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
            switch (direccion) {
                case INSERTAR_MENSAJE:
                    respuesta = insertarMensaje(request);
                    break;
                case LEER_MENSAJE:
                    respuesta = leerMensaje(request);
                    break;
                case CONSULTAR_POR_ID:
                    respuesta = consultarPorID(request);
                    break;
            }
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al procesar la petición");
            e.printStackTrace();
        } finally {
            String json = new Gson().toJson(respuesta);
            out.print(json);
            out.close();
        }
    }

    private Respuesta insertarMensaje(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        String json = request.getParameter("mensaje");
        Contacto contacto = gson.fromJson(json, Contacto.class);
        return delegadoContacto.insertarMensaje(contacto);
    }

    private Respuesta leerMensaje(HttpServletRequest request) throws IOException {
//        Gson gson = new Gson();
//        String json = request.getParameter("mensaje");
//        Contacto contacto = gson.fromJson(json, Contacto.class);        
        return delegadoContacto.consultarMensajesNoLeidos(1, 100);
    }

    private Respuesta consultarPorID(HttpServletRequest request) throws IOException {
        int idContacto = Integer.parseInt(request.getParameter("idContacto"));
        return delegadoContacto.consultarPorID(idContacto);
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
