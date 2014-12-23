/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Rol;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class RolDAO extends GenericoDAO<Rol> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolDAO(EntityManager em) {
        super(Rol.class);
        this.em = em;
    }
}
