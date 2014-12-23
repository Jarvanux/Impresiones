/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Tamano;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class TamanoDAO extends GenericoDAO<Tamano> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TamanoDAO(EntityManager em) {
        super(Tamano.class);
        this.em = em;
    }
}
