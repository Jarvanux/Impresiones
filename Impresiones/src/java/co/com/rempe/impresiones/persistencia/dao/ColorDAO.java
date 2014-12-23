/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Color;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class ColorDAO extends GenericoDAO<Color> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColorDAO(EntityManager em) {
        super(Color.class);
        this.em = em;
    }
}
