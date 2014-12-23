/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.progredi.impresiones.persistencia.entidades.Clip;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.ClipDAO;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class ClipDelegado {

    private static ClipDelegado instancia = new ClipDelegado();

    public ClipDelegado() {
    }

    public static ClipDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarClip(Clip clip) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ClipDAO clipDAO = new ClipDAO(em);
            clipDAO.crear(clip);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Clip insertado.");
        } catch (Exception ex) {
            Logger.getLogger(ChatDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error Insertando clip.");
        }
        return respuesta;
    }
}
