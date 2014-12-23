/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.TipoImpresion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */

public class TipoImpresionDAO extends GenericoDAO<TipoImpresion>{
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoImpresionDAO(EntityManager em) {
        super(TipoImpresion.class);
        this.em=em;
    }
    public TipoImpresion consultarTipoPorID(Integer id){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from tipo_impresion where id_tipo_impresion = ?", TipoImpresion.class);
            q.setParameter(1, id);
            return (TipoImpresion) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error consultar tipo impresión por ID.");
            return null;
        }
    }
    
}
