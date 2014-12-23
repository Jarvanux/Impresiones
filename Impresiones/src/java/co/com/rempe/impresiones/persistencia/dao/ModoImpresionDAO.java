
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.ModoImpresion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
public class ModoImpresionDAO extends GenericoDAO<ModoImpresion>{

    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public ModoImpresionDAO(EntityManager em){
        super(ModoImpresion.class);
        this.em = em;
    }
    public ModoImpresion consultarModoImpresion(int idModo){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from modo_impresion where id_modo_impresion = ?",ModoImpresion.class);
            q.setParameter(1, idModo);
            return (ModoImpresion)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
