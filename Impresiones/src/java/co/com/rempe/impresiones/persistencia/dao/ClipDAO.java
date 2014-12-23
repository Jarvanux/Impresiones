/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.progredi.impresiones.persistencia.entidades.Clip;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class ClipDAO extends GenericoDAO<Clip> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClipDAO(EntityManager em) {
        super(Clip.class);
        this.em = em;
    }
}
