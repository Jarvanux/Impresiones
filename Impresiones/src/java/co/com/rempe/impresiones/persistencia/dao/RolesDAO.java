/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.Roles;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class RolesDAO extends GenericoDAO<Roles> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesDAO(EntityManager em) {
        super(Roles.class);
        this.em = em;
    } 
}
