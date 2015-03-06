/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.servlets;

import co.com.rempe.impresiones.negocio.constantes.EDireccion;
import co.com.rempe.impresiones.negocio.delegado.ImpresionDelegado;
import co.com.rempe.impresiones.negocio.delegado.ModoImpresionDelegado;
import co.com.rempe.impresiones.negocio.delegado.TamanoPapelDelegado;
import co.com.rempe.impresiones.negocio.delegado.TipoImpresionDelegado;
import co.com.rempe.impresiones.negocio.delegado.TipoPapelDelegado;
import co.com.rempe.impresiones.negocio.delegado.UsuarioDelegado;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.CalcularPaginas;
import co.com.rempe.impresiones.persistencia.entidades.CostosMantenimiento;
import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import co.com.rempe.impresiones.persistencia.entidades.PorcentajeGanacia;
import co.com.rempe.impresiones.persistencia.entidades.RelacionesPapel;
import co.com.rempe.impresiones.persistencia.entidades.TipoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
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
//Instancias... Descripción de cada una en la clase EDireccion;
@WebServlet(name = "ImpresionLaserServlet", urlPatterns = {"/tipoimpresion",
    "/tipoimpresionid", "/tipopapel", "/tamanopapel", "/modoimpresion",
    "/insertarprimerform", "/imgmodoimpre", "/calcularvalorimpre",
    "/calcularpaginas", "/colores", "/tipoPlastico", "/serviciosCorte",
    "/asignarDatosImpre", "/consultarValorAnillado", "/consultarValorPlastificado",
    "/consultarValorCorte", "/guardarImpresionLaser", "/listaImpresionesLaser",
    "/filtrarImpresionLaser", "/ultimaImpresionLaser", "/consultarPrecios",
    "/actualizarCostosMantenimiento", "/actualizarNombrePapel", "/actualizarPrecioPapel",
    "/actualizarPorcentaje", "/elminarTipoPapel", "/addTipoPapel", "/consultarValoresImpresion",
    "/crearRelacionPapel", "/consultarConfiguraciones"
})

public class ImpresionServlet extends HttpServlet {

    private TipoImpresionDelegado tipoImpresionDelegado;
    private TipoPapelDelegado tipoPapelDelegado;
    private ModoImpresionDelegado modoImpresionDelegado;
    private TamanoPapelDelegado tamanoPapelDelegado;
    private ImpresionDelegado impresionLaserDelegado;

    @Override
    public void init() throws ServletException {
        super.init();
        tipoPapelDelegado = TipoPapelDelegado.getInstancia();
        tipoImpresionDelegado = TipoImpresionDelegado.getInstancia();
        modoImpresionDelegado = ModoImpresionDelegado.getInstancia();
        tamanoPapelDelegado = TamanoPapelDelegado.getInstancia();
        impresionLaserDelegado = ImpresionDelegado.getInstancia();
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
                case GUARDAR_IMPRESION_LASER:
                    respuesta = guardarImpresionLaser(request);
                    break;
                case LISTAR_IMPRESION_LASER:
                    respuesta = listarImpresionesLaser(request);
                    break;
                case FILTRAR_IMPRESION_LASER:
                    respuesta = filtrarImpresionLaser(request);
                    break;
                case ULTIMA_IMPRESION_LASER:
                    respuesta = ultimaImpresionLaser(request);
                    break;
                case CONSULTAR_PRECIOS:
                    respuesta = consultarPrecios(request);
                    break;
                case ACTUALIZAR_COSTOSMANTENIMIENTO:
                    respuesta = actualizarCostosMantenimiento(request);
                    break;
                case ACTUALIZAR_NOMBRE_PAPEL:
                    respuesta = actualizarNombrePapel(request);
                    break;
                case ACTUALIZAR_PRECIO_PAPEL:
                    respuesta = actualizarPrecioPapel(request);
                    break;
                case ACTUALIZAR_PORCENTAJE_GANANCIA:
                    respuesta = actualizarPorcentajeGanancia(request);
                    break;
                case ELIMINAR_TIPO_PALPEL:
                    respuesta = eliminaTipoPapel(request);
                    break;
                case AGREGAR_TIPO_PALPEL:
                    respuesta = agregarTipoPapel(request);
                    break;
                case CONSULTAR_VALORES_IMPRESION:
                    respuesta = consultarValoresImpresion(request);
                    break;
                case CREAR_RELACION_PAPEL:
                    respuesta = crearRelacionPapel(request);
                    break;
                case CONSULTAR_CONFIGURACIONES:
                    respuesta = consultarConfiguraciones(request);
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
//        System.out.println(cadena);
        return impresionLaserDelegado.calculaNumPaginas(cadena);
    }

    private Respuesta colores() throws IOException {
        return impresionLaserDelegado.colores();
    }

    private Respuesta tipoPlastico() throws IOException {
        return impresionLaserDelegado.tipoPlastico();
    }

    private Respuesta tipoCorte() throws IOException {
        return impresionLaserDelegado.tipoCorte();
    }

    private Respuesta genDatosImpre() throws IOException {
        return impresionLaserDelegado.asigDataImpre();
    }

    private Respuesta consultaValorAnillado(HttpServletRequest request) throws IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(1, numero);
    }

    private Respuesta consultaValorPlastificado(HttpServletRequest request) throws IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(2, numero);
    }

    private Respuesta consultaValorCorte(HttpServletRequest request) throws IOException {
        int numero = Integer.parseInt(request.getParameter("numero"));
        return impresionLaserDelegado.consultarValorServicio(5, numero);
    }

    private Respuesta guardarImpresionLaser(HttpServletRequest request) throws IOException {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
        ImpresionLaser data = gson.fromJson(json, ImpresionLaser.class);
        return impresionLaserDelegado.guardarImpresionLaser(data, idUsuario);
    }

    private Respuesta listarImpresionesLaser(HttpServletRequest request) {
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
        int tipoImpresion = Integer.parseInt(request.getParameter("tipo"));
        return impresionLaserDelegado.listarImpresionesLaser(idUsuario, tipoImpresion);
    }

    private Respuesta filtrarImpresionLaser(HttpServletRequest request) {
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
        String filtro = request.getParameter("filtro");
        int tipoImpresion = Integer.parseInt(request.getParameter("tipo"));
        return impresionLaserDelegado.filtrarImpresionLaser(idUsuario, filtro, tipoImpresion);
    }

    private Respuesta ultimaImpresionLaser(HttpServletRequest request) {
        Usuarios usuario = UsuarioDelegado.getInstancia().consultaUsuarioLogeado(request);
        System.err.println("Id Usuario: " + usuario.getIdUsuario());
        return impresionLaserDelegado.ultimaImpresionLaser(usuario);
    }

    private Respuesta consultarPrecios(HttpServletRequest request) {
        return impresionLaserDelegado.consultarPrecios(request);
    }

    private Respuesta actualizarCostosMantenimiento(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("dataBn");
        System.out.println(json);
        CostosMantenimiento dataBn = gson.fromJson(json, CostosMantenimiento.class);
        json = request.getParameter("dataColor");
        CostosMantenimiento dataColor = gson.fromJson(json, CostosMantenimiento.class);
        return impresionLaserDelegado.actualizarCostosMantenimiento(dataBn, dataColor);
    }

    private Respuesta actualizarNombrePapel(HttpServletRequest request) {
        int idPapel = Integer.parseInt(request.getParameter("idPapel"));
        String nuevoNombre = request.getParameter("nuevoNombre");
        return impresionLaserDelegado.actualizarNombrePapel(idPapel, nuevoNombre);
    }

    private Respuesta actualizarPrecioPapel(HttpServletRequest request) {
        int idPapel = Integer.parseInt(request.getParameter("idPapel"));
        double precioPapel = Double.parseDouble(request.getParameter("nuevoPrecio"));
        return impresionLaserDelegado.actualizarPrecioPapel(idPapel, precioPapel);
    }

    private Respuesta actualizarPorcentajeGanancia(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        System.out.println(json);
        PorcentajeGanacia obj = gson.fromJson(json, PorcentajeGanacia.class);
        return impresionLaserDelegado.actualizarPorcentajeGanancia(obj);
    }

    private Respuesta eliminaTipoPapel(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        return impresionLaserDelegado.eliminarTipoPapel(id);
    }

    private Respuesta agregarTipoPapel(HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("data");
        TipoPapel obj = gson.fromJson(json, TipoPapel.class);
        System.out.println("PAPEL " + obj.getPrecioPliego());
        return impresionLaserDelegado.agregarTipoPapel(obj);
    }

    private Respuesta consultarValoresImpresion(HttpServletRequest request) {
        int idPapel = Integer.parseInt(request.getParameter("idPapel"));
        int tipoColor = Integer.parseInt(request.getParameter("tipoColor"));
        int idTipoTamano = Integer.parseInt(request.getParameter("idTipoTamano"));
        int numPag = Integer.parseInt(request.getParameter("numPag"));
        return impresionLaserDelegado.consultarValoresImpresion(idPapel, tipoColor, idTipoTamano, numPag);
    }

    private Respuesta crearRelacionPapel(HttpServletRequest request) {
        return impresionLaserDelegado.crearRelacion(request);
    }

    private Respuesta consultarConfiguraciones(HttpServletRequest request) {
        int idPapel = Integer.parseInt(request.getParameter("idPapel"));
        return impresionLaserDelegado.consultarRelaciones(idPapel);
    }

}
