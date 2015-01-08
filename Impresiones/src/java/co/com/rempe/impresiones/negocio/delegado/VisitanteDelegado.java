/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.VisitantesDAO;
import co.com.rempe.impresiones.persistencia.entidades.Visitantes;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class VisitanteDelegado {

    private static VisitanteDelegado instancia = new VisitanteDelegado();

    public VisitanteDelegado() {

    }

    public static VisitanteDelegado getInstancia() {
        return instancia;
    }

    public Respuesta guardarVisitante(String ipUsuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            Visitantes visitante = new Visitantes();
            VisitantesDAO dao = new VisitantesDAO(em);
            visitante.setIpComputadora(Long.parseLong(ipUsuario));
            visitante.setFecha(new Date());
            dao.crear(visitante);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }
}
