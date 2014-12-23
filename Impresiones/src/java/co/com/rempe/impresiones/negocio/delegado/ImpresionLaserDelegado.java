/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.CalcularPaginas;
import co.com.rempe.impresiones.negocio.utilerias.DatosRespuesta;
import co.com.rempe.impresiones.negocio.utilerias.Utilerias;
import co.com.rempe.impresiones.negocio.utilerias.ValorImpresion;
import co.com.rempe.impresiones.persistencia.dao.ColorDAO;
import co.com.rempe.impresiones.persistencia.dao.CostosMantenimientoDAO;
import co.com.rempe.impresiones.persistencia.dao.ImpresionLaserDAO;
import co.com.rempe.impresiones.persistencia.dao.ModoImpresionDAO;
import co.com.rempe.impresiones.persistencia.dao.PorcentajeGananciaDAO;
import co.com.rempe.impresiones.persistencia.dao.ServiciosAdicionalesDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoCorteDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoPapelDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoPlasticoDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoTamanoDAO;
import co.com.rempe.impresiones.persistencia.entidades.Color;
import co.com.rempe.impresiones.persistencia.entidades.CostosMantenimiento;
import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import co.com.rempe.impresiones.persistencia.entidades.ModoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.PorcentajeGanacia;
import co.com.rempe.impresiones.persistencia.entidades.ServiciosAdicionales;
import co.com.rempe.impresiones.persistencia.entidades.TipoCorte;
import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import co.com.rempe.impresiones.persistencia.entidades.TipoPlastico;
import co.com.rempe.impresiones.persistencia.entidades.TipoTamano;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class ImpresionLaserDelegado {

    private static ImpresionLaserDelegado instancia = new ImpresionLaserDelegado();

    public ImpresionLaserDelegado() {
    }

    public static ImpresionLaserDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarPrimerForm(ImpresionLaser impresionLaser) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            System.out.println("Tipo Color: " + impresionLaser.getTipoColor());
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
            dao.crear(impresionLaser);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Primeros parámetros de la impresión guarddos.");
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(ImpresionLaserDelegado.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al insertar.");
            return respuesta;
        }
    }

    public Respuesta consultarModoImpre(int idModo) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ModoImpresion objetoRespuesta = new ModoImpresion();
            ModoImpresionDAO dao = new ModoImpresionDAO(em);
            objetoRespuesta = dao.consultarModoImpresion(idModo);
            int codigo = (objetoRespuesta == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(objetoRespuesta);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(ImpresionLaserDelegado.class.getName()).log(Level.SEVERE, null, e);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los datos solicitados.");
            return respuesta;
        }
    }

    public Respuesta colores() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ColorDAO dao = new ColorDAO(em);
            List<Color> listaColores = dao.buscarTodos();
            int codigo = (listaColores.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaColores);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tamaños de impresión");
            return respuesta;
        }
    }
    
    public Respuesta consultarValorServicio(int codigo,int numData){
        EntityManager em;
        Respuesta respuesta = new  Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ServiciosAdicionales objetoRespuesta = new ServiciosAdicionales();
            ServiciosAdicionalesDAO dao = new ServiciosAdicionalesDAO(em);
            objetoRespuesta = dao.consultarValores(codigo, numData);
            int codigo2 = (objetoRespuesta == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo2);
            respuesta.setDatos(objetoRespuesta);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");
            return respuesta;
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo consultar los datos. ERROR: "+e.getMessage());
            return respuesta;
        }
    }

    public Respuesta tipoPlastico() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoPlasticoDAO dao = new TipoPlasticoDAO(em);
            List<TipoPlastico> listaPlasticos = dao.buscarTodos();
            int codigo = (listaPlasticos.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setDatos(listaPlasticos);
            respuesta.setCodigo(codigo);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar datos solicitados.");
            return respuesta;
        }
    }

    public Respuesta tipoCorte() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoCorteDAO dao = new TipoCorteDAO(em);
            List<TipoCorte> lista = dao.buscarTodos();
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(lista);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tamaños de impresión");
            return respuesta;
        }
    }

    public Respuesta calculaNumPaginas(String cadena) {
        Respuesta respuesta = new Respuesta();
        System.out.println(cadena);
        try {
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            String resultado = CalcularPaginas.controlCalculo(cadena);
            String[] arrayTemp = resultado.split("/");
            DatosRespuesta datos = new DatosRespuesta();
            if (resultado.contains("|") || (Integer.parseInt(arrayTemp[0])) == 0) {
                respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
                datos.setString(arrayTemp[2]);
                respuesta.setDatos(datos);
                if (arrayTemp[1].startsWith("|")) {
                    respuesta.setMensaje(arrayTemp[1].substring(1));
                } else {
                    respuesta.setMensaje(arrayTemp[1]);
                }
            } else {
                respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
                datos.setEntero(Integer.parseInt(arrayTemp[0]));
                datos.setString(arrayTemp[2]); //Vendría siendo la cadena corregida.
                respuesta.setDatos(datos);
                respuesta.setMensaje("Se ha calculado exitosamente el número de páginas.");
            }
            return respuesta;

        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error directamente desde el servlet, contacte el desarrollador.");
            return respuesta;
        }
    }

    //Calcular valor de una impresión.
    public Respuesta calcularValorImpre(int tipoColor, int idModo, int tamano, int papel, int numHojas) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            //Cosultamos el modo de impresión que ha seleccionado el usuario.
//            ModoImpresion modoImpresion = new ModoImpresion();
//            ModoImpresionDAO modoImpresionDAO = new ModoImpresionDAO(em);
//            modoImpresion = modoImpresionDAO.consultarModoImpresion(idModo);
            //Fin consulta modo impresión.

            //Consultamos el tamaño seleccionado.
            TipoTamano tipoTamano = new TipoTamano();
            TipoTamanoDAO tipoTamanoDAO = new TipoTamanoDAO(em);
            tipoTamano = tipoTamanoDAO.consultarTipoTamano(tamano);
            //Fin consulta tamaño.

            //Consultamos el papel.
            TipoPapel tipoPapel = new TipoPapel();
            TipoPapelDAO tipoPapelDAO = new TipoPapelDAO(em);
            tipoPapel = tipoPapelDAO.consultarTipoPapelPorID(papel);
            //Fin consulta papel.
//
            //Consultamos los costos de mantenimiento según el tipo de color seleccionado.
            CostosMantenimiento costosMantenimiento = new CostosMantenimiento();
            CostosMantenimientoDAO costosMantenimientoDAO = new CostosMantenimientoDAO(em);
            costosMantenimiento = costosMantenimientoDAO.consultarCostosMantenimientoID(tipoColor);
            //Fin consulta costos mantenimiento.

            //Una vez consultados los parámetros necesarios para hacer el calculo 
            //vamos a realizar algunas condiciones.            
            double precioPliego = tipoPapel.getPrecioPliego();
            double numeroHojas = tipoTamano.getNumeroHojas();

            //Si tipoColor = 0 la impresión es a B/N, si es 1 es a Color
            double valorFinal = precioPliego / numeroHojas;

            System.out.println(valorFinal);

            //Multiplicamos El costo de mantenimiento * el tipo de papel + el valor de la impresión.
            double costoMantent = costosMantenimiento.getValorCosto();
            System.out.println(costoMantent);
            //Una vez multiplicado el costo de mantenimiento * el tipo del papel, es hora de sumar.
            costoMantent *= tipoTamano.getCostoImpresion();
            System.out.println(costoMantent);
            valorFinal = (valorFinal + costoMantent);
            System.out.println(valorFinal);
            ValorImpresion valorImpresion = new ValorImpresion();

            PorcentajeGanacia porcentajeGanancia = new PorcentajeGanacia();
            PorcentajeGananciaDAO porcentajeGananciaDAO = new PorcentajeGananciaDAO(em);
            porcentajeGanancia = porcentajeGananciaDAO.consultarGanancia(numHojas, tipoColor);

            valorFinal *= porcentajeGanancia.getPorcentaje() / 100;
            valorImpresion.setValorImpresion(Utilerias.redondearDecimal(valorFinal));

//            ValorCosto * %Ganancia / 100.
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(valorImpresion);
            respuesta.setMensaje("Valor de impresión calculado existosamente!.");
            return respuesta;
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al calcular el valor de la impresión.");
            return respuesta;
        }
    }
    //Fin calcular valor de una impresión.
    
    
    public Respuesta asigDataImpre(){
        Respuesta respuesta = new Respuesta();
        try {
            DatosRespuesta datos = new DatosRespuesta();
            datos.setEnteroGrande(System.currentTimeMillis());            
            datos.setFecha(new Date());            
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(datos);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }

}
