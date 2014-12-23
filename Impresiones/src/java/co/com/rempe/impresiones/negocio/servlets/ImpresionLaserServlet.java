/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.ImpresionLaserDelegado;
import co.com.rempe.impresiones.negocio.delegado.ModoImpresionDelegado;
import co.com.rempe.impresiones.negocio.delegado.TamanoPapelDelegado;
import co.com.rempe.impresiones.negocio.delegado.TipoImpresionDelegado;
import co.com.rempe.impresiones.negocio.delegado.TipoPapelDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.CalcularPaginas;
import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import co.com.rempe.impresiones.persistencia.entidades.TipoImpresion;
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
@WebServlet(name = "ImpresionLaserServlet", urlPatterns = {"/tipoimpresion", "/tipoimpresionid", "/tipopapel", "/tamanopapel", "/modoimpresion", "/insertarprimerform", "/imgmodoimpre", "/calcularvalorimpre", "/calcularpaginas","/colores","/tipoPlastico","/serviciosCorte","/asignarDatosImpre","/consultarValorAnillado","/consultarValorPlastificado","/consultarValorCorte"})
public class ImpresionLaserServlet extends HttpServlet {

    private TipoImpresionDelegado tipoImpresionDelegado;
    private TipoPapelDelegado tipoPapelDelegado;
    private ModoImpresionDelegado modoImpresionDelegado;
    private TamanoPapelDelegado tamanoPapelDelegado;
    private ImpresionLaserDelegado impresionLaserDelegado;

    @Override
    public void init() throws ServletException {
        super.init();
        tipoPapelDelegado = TipoPapelDelegado.getInstancia();
        tipoImpresionDelegado = TipoImpresionDelegado.getInstancia();
        modoImpresionDelegado = ModoImpresionDelegado.getInstancia();
        tamanoPapelDelegado = TamanoPapelDelegado.getInstancia();
        impresionLaserDelegado = ImpresionLaserDelegado.getInstancia();
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
                case TIPO_IMPRESION:
                    respuesta = listarTipoImpresion();
                    break;
                case TIPO_PAPEL:
                    respuesta = consultarTipoPapel();
                    break;
                case TAMANO_PAPEL:
                    respuesta = listarTipoTamano();
                    break;
                case MODO_IMPRESION:
                    respuesta = listarModosImpesion();
                    break;
                case INSERTAR_PRIMERFORM:
                    respuesta = insertarPrimerForm(request);
                    break;
                case TIPO_IMPRESIONID:
                    respuesta = tipoImpresionID(request);
                    break;
                case CONSULTAR_IMG:
                    respuesta = consultarModoImpre(request);
                    break;
                case CALCULAR_VALOR_IMPRESION:
                    respuesta = calcularValorImpresion(request);
                    break;
                case CALCULAR_PAGINAS:
                    respuesta = calcularPaginas(request);
                    break;
                case COLORES:
                    respuesta = colores();
                    break;
                case TIPO_PLASTICO:
                    respuesta = tipoPlastico();
                    break;
                case TIPO_CORTE:
                    respuesta = tipoCorte();
                    break;
                case ASIG_DATOS_IMPRE:
                    respuesta = genDatosImpre();
                    break;
                case CONSULTA_VALOR_ANILLADO:
                    respuesta = consultaValorAnillado(request);
                    break;
                case CONSULTA_VALOR_PLASTIFICADO:
                    respuesta = consultaValorPlastificado(request);
                    break;
                case CONSULTA_VALOR_CORTE:
                    respuesta = consultaValorCorte(request);
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

    private Respuesta listarTipoImpresion() {
        return tipoImpresionDelegado.consultarTiposImpresion();
    }

    private Respuesta consultarTipoPapel() {
        return tipoPapelDelegado.consultarTipoPapel();
    }

    private Respuesta listarModosImpesion() {
        return modoImpresionDelegado.listarModoImpresion();
    }

    private Respuesta listarTipoTamano() {
        return tamanoPapelDelegado.consultarTamanoPapel();
    }

    private Respuesta insertarPrimerForm(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        String json = request.getParameter("impresionlaser");
        ImpresionLaser impresionLaser = gson.fromJson(json, ImpresionLaser.class);
        return impresionLaserDelegado.insertarPrimerForm(impresionLaser);
    }

    private Respuesta tipoImpresionID(HttpServletRequest request) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("idTipoImpresion"));
        return tipoImpresionDelegado.consultarTipoImprsionPorID(id);
    }

    private Respuesta consultarModoImpre(HttpServletRequest request) throws IOException {
        int id = Integer.parseInt(request.getParameter("idModoSelect"));
        return impresionLaserDelegado.consultarModoImpre(id);
    }

    private Respuesta calcularValorImpresion(HttpServletRequest request) throws IOException {
        int idTipoColor = Integer.parseInt(request.getParameter("idTipoColor"));
        int idModoImpre = Integer.parseInt(request.getParameter("idModoImpre"));
        int idTamanoImpre = Integer.parseInt(request.getParameter("idTamanoPapel"));
        int idTipoPapel = Integer.parseInt(request.getParameter("idTipoPapel"));
        int numeroHojas = Integer.parseInt(request.getParameter("numeroHojas"));
        return impresionLaserDelegado.calcularValorImpre(idTipoColor, idModoImpre, idTamanoImpre, idTipoPapel, numeroHojas);
    }

    private Respuesta calcularPaginas(HttpServletRequest request) throws IOException {
        String cadena = request.getParameter("cadena");
        System.out.println(cadena);
        return impresionLaserDelegado.calculaNumPaginas(cadena);
    }
    
    private Respuesta colores() throws IOException{
        return impresionLaserDelegado.colores();
    }
    
    private Respuesta tipoPlastico() throws IOException{
        return impresionLaserDelegado.tipoPlastico();
    }
    private Respuesta tipoCorte() throws IOException{
        return impresionLaserDelegado.tipoCorte();
    }
    private Respuesta genDatosImpre() throws IOException{
        return impresionLaserDelegado.asigDataImpre();
    }
    
    private Respuesta consultaValorAnillado(HttpServletRequest request) throws IOException{        
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(1, numero);
    }
    private Respuesta consultaValorPlastificado(HttpServletRequest request) throws IOException{
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(2, numero);
    }
    private Respuesta consultaValorCorte(HttpServletRequest request) throws IOException{
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(3, numero);
    }
    
}
