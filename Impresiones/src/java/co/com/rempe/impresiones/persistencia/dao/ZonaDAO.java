/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Zona;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */

public class ZonaDAO extends GenericoDAO<Zona>  {
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonaDAO() {
        super(Zona.class);
    }
    
}
