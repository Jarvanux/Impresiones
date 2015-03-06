/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.entidades.ArchivosAdjuntos;
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
@WebServlet(name = "SubirArchivosServlet", urlPatterns = {"/subirArchivosServlet", "/eliminarArchivo"})
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
                case ELIMINAR_ARCHIVO:
                    respuesta = eliminarArchivo(request);
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
//        System.out.println("Items-------");
        List items = upload.parseRequest(request);
        System.out.println(items);

//        System.out.println("Request-----------------");
        System.out.println(request);
// Se recorren todos los items, que son de tipo FileItem
        for (Object item : items) {
            FileItem uploaded = (FileItem) item;
            // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
            // subido donde nos interese
//            System.out.println("Item---------------");
            System.out.println(uploaded.isFormField());
            if (!uploaded.isFormField()) {
                // No es campo de formulario, guardamos el fichero en algún sitio

                //El sisguiente bloque simplemente es para divorciar el nombre del archivo de las rutas
                //posibles que pueda traernos el getName() sobre el objeto de  la clase FileItem,
                //pues he descuvierto que el explorer especificamente es el único que envía
                //la ruta adjuntada al nombre, por lo cual es importante corregirlo.
                String nombreArchivo = uploaded.getName();
                String cadena = nombreArchivo;
                System.out.println(cadena);
                while (cadena.contains("\\")) {
                    cadena = cadena.replace("\\", "&");
                }
//                System.out.println(cadena);
                String[] ruta = cadena.split("&");
                for (int i = 0; i < ruta.length; i++) {
                    String string = ruta[i];
                    System.out.println(string);
                }
                nombreArchivo = ruta[ruta.length - 1];
//                System.out.println("Ruta archivo: " + nombreArchivo);
                //Fin corrección nombre.

                String nombreArchivoEscrito = System.currentTimeMillis() + "-" + nombreArchivo;
                String rutaEscritura = new File(request.getRealPath("archivos-subidos"), nombreArchivoEscrito).toString();
                File fichero = new File(rutaEscritura);

                ArchivosAdjuntos archivo = new ArchivosAdjuntos();
                archivo.setNombreArchivo(nombreArchivo);
                archivo.setRutaArchivo(rutaEscritura);
                archivo.setTamanioArchivo(uploaded.getSize());
                if (nombreArchivo.endsWith(".pdf") || nombreArchivo.endsWith(".png")
                        || nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".bmp")
                        || nombreArchivo.endsWith(".svg")) {
//                    System.out.println("Archivo subido: " + uploaded.getName());
                    uploaded.write(fichero);
                    respuesta.setCodigo(1);
                    respuesta.setDatos(archivo);
                    respuesta.setMensaje("Se ha subido exitosamente el archivo: " + uploaded.getName());
                } else {
                    respuesta.setCodigo(0);
                    respuesta.setDatos(archivo);
                    respuesta.setMensaje("El formato del archivo " + nombreArchivo + " es invalido!");
                }
            }
//            else {
//                // es un campo de formulario, podemos obtener clave y valor
//                String key = uploaded.getFieldName();
//                String valor = uploaded.getString();
//                System.out.println("Archivo subido: " + key);
//            }
        }
        return respuesta;
    }

    private Respuesta eliminarArchivo(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        try {
            File archivo = new File(request.getParameter("rutaArchivo"));
            if (archivo.delete()) {
                respuesta.setCodigo(1);
                respuesta.setMensaje("Se ha eliminado el archivo!.");
            }else{
                respuesta.setCodigo(0);
                respuesta.setMensaje("No se pudo eliminar el archivo.");
            }
        } catch (Exception e) {
            respuesta.setCodigo(-1);
            respuesta.setMensaje("Error al eliminar el archivo!.");
        }
        return respuesta;
    }
}
