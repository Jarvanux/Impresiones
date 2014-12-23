/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Producto;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */

public class ProductoDAO extends GenericoDAO<Producto> {
    
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoDAO(EntityManager em) {
        super(Producto.class);
        this.em=em;
    }
    
}
