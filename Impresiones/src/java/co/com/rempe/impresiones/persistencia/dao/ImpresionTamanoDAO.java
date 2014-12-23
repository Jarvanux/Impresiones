/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.ImpresionTamano;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class ImpresionTamanoDAO extends GenericoDAO<ImpresionTamano> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpresionTamanoDAO(EntityManager em) {
        super(ImpresionTamano.class);
        this.em = em;
    }
}
