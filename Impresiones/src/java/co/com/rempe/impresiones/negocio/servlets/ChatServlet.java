/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.ChatDelegado;
import co.com.rempe.impresiones.negocio.delegado.UsuarioDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@WebServlet(name = "ChatServlet", urlPatterns = {"/insertarchat", "/leerchat", "/servlet", "/consultaClientes", "/mensajeDeBuzon", "/listaMensajesBuzon","/actualizarSMSLeido"})
public class ChatServlet extends HttpServlet {

    private ChatDelegado chatDelegado;

    @Override
    public void init() throws ServletException {
        super.init();
        chatDelegado = ChatDelegado.getInstancia();
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
                case INSERTAR_CHAT:
                    respuesta = insertarChat(request);
                    break;
                case LEER_CHAT:
                    respuesta = leerChat(request);
                    break;
                case SERVLET:
                    respuesta = servlet(request);
                    break;
                case CONSULTA_CLIENTES:
                    respuesta = consultaClientes(request);
                    break;
                case MENSAJE_BUZON:
                    respuesta = mensajeBuzon(request);
                    break;
                case LISTA_MENSAJE_BUZON:
                    respuesta = listaMensjesBuzon(request);
                    break;
                case ACTUALIZA_MENSAJE_BUZON:
                    respuesta = actualizaMensajeBuzon(request);
                    break;                
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            String json = new Gson().toJson(respuesta);
            out.print(json);
            out.close();
        }
    }

    private Respuesta servlet(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
//        System.out.println("Color: " + request.getParameter("color"));
//        System.out.println("ID: " + request.getParameter("id"));
        respuesta.setCodigo(1);
        respuesta.setMensaje("ID: " + request.getParameter("id") + " Color: " + request.getParameter("color"));
        return respuesta;
    }

    private Respuesta insertarChat(HttpServletRequest request) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json = request.getParameter("chat");
        Chat chat = gson.fromJson(json, Chat.class);
        if (chat != null) {
            if (chat.getIdConversacion() < 0) {
                chat.setIdConversacion(System.currentTimeMillis());
            }
        }
        return chatDelegado.insertarChat(chat);
    }

    private Respuesta leerChat(HttpServletRequest request) {
        int accion = Integer.parseInt(request.getParameter("accion"));
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
        long idUsuarioContacto = Long.parseLong(request.getParameter("idUsuarioContacto"));
        long idConversacion = 0;
        return chatDelegado.leerChat(accion, idUsuario, idUsuarioContacto, idConversacion);
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
    private Respuesta consultaClientes(HttpServletRequest request) {
        int idAdministrador = 0;
        UsuarioDelegado usuarioDelegado = UsuarioDelegado.getInstancia();
        Respuesta respuesta = usuarioDelegado.consultarSession(request);
        Usuarios usuarios = (Usuarios) respuesta.getDatos();
//        HttpSession session = request.getSession();
//        session.setAttribute("usuario", usuarios);
//        if (usuarios != null) {
//            System.out.println(session.getAttribute("usuario"));
//        }
        idAdministrador = usuarios.getIdUsuario();
        return chatDelegado.consultaClientes(idAdministrador);
    }

    private Respuesta mensajeBuzon(HttpServletRequest request) { //Inserta un mensaje nuevo.
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correoE");
        String mensaje = request.getParameter("mensaje");
        return chatDelegado.mensajeBuzon(nombre, correo, mensaje);
    }

    private Respuesta listaMensjesBuzon(HttpServletRequest request) {
        int codigoLeido = Integer.parseInt(request.getParameter("codigoLeido"));
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        String desde = request.getParameter("desde");
        return chatDelegado.listaMensajesBuzon(codigoLeido,fecha1,fecha2,desde);
    }

    private Respuesta actualizaMensajeBuzon(HttpServletRequest request) {
        int idSMS = Integer.parseInt(request.getParameter("idSMS"));
        int idUsuarioLector = Integer.parseInt(request.getParameter("idUsuario"));
        return chatDelegado.actualizaMensajeBuzaon(idSMS,idUsuarioLector);
    }
        
}
