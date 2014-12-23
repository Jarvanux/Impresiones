/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class TipoPapelDAO extends GenericoDAO<TipoPapel> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPapelDAO(EntityManager em) {
        super(TipoPapel.class);
        this.em = em; 
    }

    public List<TipoPapel> consultarTipoPapel(int idTipoImpresion) {
        String sql =
                "select "
                + "tp.* "
                + "from "
                + "tipo_papel tp inner join  impresion_papel ip on tp.id_tipo_papel=ip.id_tipo_papel "
                + "inner join tipo_impresion ti on ip.id_tipo_impresion=ti.id_tipo_impresion "
                + "where "
                + "ti.id_tipo_impresion=? ";
        Query q = em.createNativeQuery(sql, TipoPapel.class);
        q.setParameter(1, idTipoImpresion);
        return q.getResultList();
    }
    
    public TipoPapel consultarTipoPapelPorID(int idTipoPapel){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from tipo_papel where id_tipo_papel = ?", TipoPapel.class);
            q.setParameter(1, idTipoPapel);
            return (TipoPapel)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
