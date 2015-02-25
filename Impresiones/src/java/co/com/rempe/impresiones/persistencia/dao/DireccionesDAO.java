/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.DireccionesUsuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class DireccionesDAO extends GenericoDAO<DireccionesUsuarios> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionesDAO(EntityManager em) {
        super(DireccionesUsuarios.class);
        this.em = em;
    }

    public List<DireccionesUsuarios> direcciones(int idUsuario) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from direcciones_usuarios where id_usuario = ?", DireccionesUsuarios.class);
            q.setParameter(1, idUsuario);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<DireccionesUsuarios> consultarDireccion(String direccion){
        try {
            Query q = null;
            q = em.createNativeQuery("select * from direcciones_usuarios where direccion = ?",DireccionesUsuarios.class);
            q.setParameter(1, direccion);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
