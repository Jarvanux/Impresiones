/*
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.ContenteditorDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.entidades.Contenteditor;
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
@WebServlet(name = "ContenteditorServlet", urlPatterns = {"/insertarContenteditor","/buscarContenteditor","/buscarPorIDContenteditor","/buscarTodoContenteditor","/eliminarContenteditor"})
public class ContenteditorServlet extends HttpServlet {

    private ContenteditorDelegado contenteditorDelegado;

    @Override
    public void init() throws ServletException {
        super.init();
        contenteditorDelegado = ContenteditorDelegado.getInstancia();
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param responseHttp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse responseHttp)
            throws ServletException, IOException {
        responseHttp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = responseHttp.getWriter();
        Respuesta respuesta = new Respuesta();
        try {
            String url = request.getServletPath();
            EDireccion direccion = EDireccion.getDireccion(url);
            switch (direccion) {
                case INSERTAR_CONTENTEDITOR:
                    respuesta = insertarContenteditor(request);
                    break;
                case BUSCAR_CONTENTEDITOR:
                    respuesta = buscarContenteditor(request);
                    break;
            }
        } catch (Throwable e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error desde el servlet >>No se pudo completar la petición.");
            e.printStackTrace();
        } finally {
            String json = new Gson().toJson(respuesta);
            out.print(json);
            out.close();
        }
    }

    private Respuesta insertarContenteditor(HttpServletRequest request) throws IOException {        
        String text = request.getParameter("text");        
        return contenteditorDelegado.insertarContenteditor(text);
    }
    private Respuesta buscarContenteditor(HttpServletRequest request) throws IOException {        
        return contenteditorDelegado.buscarContenteditor();
    }   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

