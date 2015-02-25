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
import co.com.rempe.impresiones.persistencia.entidades.DireccionesUsuarios;
import co.com.rempe.impresiones.persistencia.entidades.Usuario;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhonjaider1000
 */
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/actualizaPerfil", "/opteneripusuario", "/registrarUsuarios", "/ingresar", "/cambiarEstado", "/consultarUsuarios", "/consultarUsuarioLogeado", "/cerrarSesion", "/buscarAsesores", "/visitanteTemporal", "/cambiarEstadoAdmin", "/listarDirecciones", "/guardarDireccion"})
public class UsuariosServlet extends HttpServlet {

    private UsuarioDelegado usuarioDelegado;
    RequestDispatcher vista;

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
        String ulr = "";
        EDireccion direccion = null;
        try {
            ulr = request.getServletPath();
            direccion = EDireccion.getDireccion(ulr);
            switch (direccion) {
                case REGISTRAR_USUARIO:
                    respuesta = insertarUsuario(request);
                    break;
                case INGRESAR:
                    respuesta = ingresar(request);
                    Usuarios usuarios = null;
                    if (respuesta.getDatos() != null) {
                        usuarios = (Usuarios) respuesta.getDatos();
                    }
                    if (usuarios != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usuario", usuarios);
                        System.out.println(session.getAttribute("usuario"));
                        usuarioDelegado.cambiarEstadoConexion(usuarios.getIdUsuario(), true);
                        usuarioDelegado.cambiarEstadoAdmin(usuarios.getIdUsuario(), 2);
                    }
                    break;
                case CAMBIAR_ESTADO:
                    respuesta = null;
                    break;
                case CONSULTAR_USUARIOS:
                    respuesta = consultarUsuarioPorID(request);
                    break;
                case CONSULTAR_USUARIO_LOGEADO:
                    respuesta = consultarUsuarioLogueado(request);
                    break;
                case CERRAR_SESION:
                    respuesta = cerrarSesion(request);
                    break;
                case BUSCAR_ASESORES:
                    respuesta = buscarAsesores();
                    break;
                case OPTENER_IP_USUARIO:
                    respuesta = optenerIpUsuario(request);
                    break;
                case GUARDAR_VISITANTE_TEMPORAL:
                    respuesta = guardarVisitanteTemporal(request);
                    break;
                case CAMBIAR_ESTADO_ADMIN:
                    respuesta = cambiarEstadoAdmin(request);
                    break;
                case ACTUALIZAR:
                    respuesta = actualizarDatos(request);
                    break;
                case LISTAR_DIRECCIONES:
                    respuesta = listarDirecciones(request);
                    break;
                case GUARDAR_DIRECCION:
                    respuesta = guardarDireccion(request);
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

    private Respuesta consultarUsuarioPorID(HttpServletRequest request) {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        return usuarioDelegado.cambiarEstado(idUsuario);
    }

    private Respuesta consultarUsuarioLogueado(HttpServletRequest request) {
        return usuarioDelegado.consultarSession(request);
    }

    private Respuesta cerrarSesion(HttpServletRequest request) {
        return usuarioDelegado.cerrarSesion(request);
    }

    private Respuesta buscarAsesores() {
        return usuarioDelegado.buscarAsesores();
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

    private Respuesta optenerIpUsuario(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        try {
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(getIp());
            respuesta.setMensaje("Ip consultada exitosamente!.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo optener el ip del cliente.");
        }
        return respuesta;
    }

    public static String getIp() throws Exception {
        return "" + System.currentTimeMillis();
    }

    private Respuesta guardarVisitanteTemporal(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        Usuarios usuario = new Usuarios();
        if (nombre.contains(" ")) {
            String[] partesNombre = nombre.split(" ");
            if (partesNombre.length == 2) {
                usuario.setNombres(partesNombre[0]);
                usuario.setApellidos(partesNombre[1]);
            } else if (partesNombre.length == 3) {
                usuario.setNombres(partesNombre[0] + ' ' + partesNombre[1]);
                usuario.setApellidos(partesNombre[2]);
            } else if (partesNombre.length == 4) {
                usuario.setNombres(partesNombre[0] + ' ' + partesNombre[1]);
                usuario.setApellidos(partesNombre[3] + ' ' + partesNombre[2]);
            } else if (partesNombre.length > 4) {
                usuario.setNombres(partesNombre[0] + ' ' + partesNombre[1]);
                String string = "";
                for (int i = 1; i < partesNombre.length; i++) {
                    string += partesNombre[1];
                }
                usuario.setApellidos(string);
            }
        } else {
            usuario.setNombres(nombre);
        }
        usuario.setEmail(correo);
        return usuarioDelegado.insertarUsuario(usuario);
    }

    private Respuesta cambiarEstadoAdmin(HttpServletRequest request) {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idEstado = Integer.parseInt(request.getParameter("estado"));
        return usuarioDelegado.cambiarEstadoAdmin(idUsuario, idEstado);
    }

    private Respuesta actualizarDatos(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        Usuarios usuario = gson.fromJson(json, Usuarios.class);
        return usuarioDelegado.actualizarDatos(usuario);
    }

    private Respuesta listarDirecciones(HttpServletRequest request) {
        return usuarioDelegado.listarDirecciones(request);
    }

    private Respuesta guardarDireccion(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        HttpSession session = request.getSession();
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        DireccionesUsuarios direcciones = gson.fromJson(json, DireccionesUsuarios.class);
        if (usuario != null) {
            direcciones.setIdUsuario(usuario.getIdUsuario());
        }
        return usuarioDelegado.guardarDireccion(direcciones);
    }

}
