/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.TipoTamano;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class TipoTamanoDAO extends GenericoDAO<TipoTamano> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTamanoDAO() {
        super(TipoTamano.class);
    }
    public TipoTamanoDAO(EntityManager em){
        super(TipoTamano.class);
        this.em = em;
    }
    
    public TipoTamano consultarTipoTamano(int idTipoTamano){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from tipo_tamano where id_tipo_tamano = ?", TipoTamano.class);
            q.setParameter(1, idTipoTamano);
            return (TipoTamano)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
