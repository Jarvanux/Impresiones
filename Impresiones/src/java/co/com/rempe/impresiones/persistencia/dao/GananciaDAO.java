/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Ganancia;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class GananciaDAO extends GenericoDAO<Ganancia> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GananciaDAO(EntityManager em) {
        super(Ganancia.class);
        this.em = em;
    }
}
