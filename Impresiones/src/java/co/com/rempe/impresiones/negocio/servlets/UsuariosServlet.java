/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.UsuarioDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.entidades.Usuario;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhonjaider1000
 */
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/registrarUsuarios", "/ingresar", "/cambiarEstado"})
public class UsuariosServlet extends HttpServlet {

    private UsuarioDelegado usuarioDelegado;

    public void init() throws ServletException {
        super.init();
        usuarioDelegado = UsuarioDelegado.getInstancia();
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
//        request.getRequestDispatcher("panelusuario.html").include(request, response);  
        PrintWriter out = response.getWriter();
        Respuesta respuesta = new Respuesta();
        Usuarios usuario = null;
//        request.getRequestDispatcher("panelusuario.html").include(request, response);
        try {
            String ulr = request.getServletPath();
            EDireccion direccion = EDireccion.getDireccion(ulr);
            switch (direccion) {
                case REGISTRAR_USUARIO:
                    respuesta = insertarUsuario(request);
                    break;
                case INGRESAR:
                    respuesta = ingresar(request);
                    break;
                case CAMBIAR_ESTADO:
                    respuesta = null;
                    break;
            }
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al procesar la petición " + e);
            e.printStackTrace();
        } finally {
            String json = new Gson().toJson(respuesta);
//            String cadena = json;
//            int index = cadena.indexOf("datos");
//            cadena = cadena.substring(index);
//            cadena = "{\"" + cadena;
//            System.out.println(cadena);
            out.print(json);
            out.close();            
        }
    }

    private Respuesta insertarUsuario(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        Usuarios usuario = gson.fromJson(json, Usuarios.class);
        return usuarioDelegado.insertarUsuario(usuario);
    }

    private Respuesta ingresar(HttpServletRequest request) throws IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        return usuarioDelegado.ingresar(usuario, password);
    }

    private Respuesta cambiarEstado(HttpServletRequest request) {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        return usuarioDelegado.cambiarEstado(idUsuario);
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
