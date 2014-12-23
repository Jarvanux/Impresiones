/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author jhonjaider1000
 */
@WebServlet(name = "SubirArchivosServlet", urlPatterns = {"/subirArchivosServlet"})
public class SubirArchivosServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Respuesta respuesta = new Respuesta();
        try {
            String url = request.getServletPath();
            EDireccion direccion = EDireccion.getDireccion(url);
            switch (direccion) {
                case SUBIR_ARCHIVO:
                    respuesta = subirArchivo(request);
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

    private Respuesta subirArchivo(HttpServletRequest request) throws FileUploadException, Exception {
        Respuesta respuesta = new Respuesta();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

// req es la HttpServletRequest que recibimos del formulario.
// Los items obtenidos serán cada uno de los campos del formulario,
// tanto campos normales como ficheros subidos.
        List items = upload.parseRequest(request);
        System.out.println(items);

// Se recorren todos los items, que son de tipo FileItem
        for (Object item : items) {
            FileItem uploaded = (FileItem) item;
            // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
            // subido donde nos interese
            if (!uploaded.isFormField()) {
                // No es campo de formulario, guardamos el fichero en algún sitio
                File fichero = new File("D:" + File.separator + "Servidor", uploaded.getName());
                System.out.println("Archivo subido: " + uploaded.getName());
                uploaded.write(fichero);
                respuesta.setCodigo(1);
                respuesta.setMensaje("Se ha subido exitosamente el archivo: "+uploaded.getName());
            } else {
                // es un campo de formulario, podemos obtener clave y valor
                String key = uploaded.getFieldName();
                String valor = uploaded.getString();
                System.out.println("Archivo subido: " + key);
            }
        }
        return respuesta;
    }
}
