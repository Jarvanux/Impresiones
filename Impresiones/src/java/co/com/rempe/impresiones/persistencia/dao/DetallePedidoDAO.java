/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.DetallePedido;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class DetallePedidoDAO extends GenericoDAO<DetallePedido> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePedidoDAO(EntityManager em) {
        super(DetallePedido.class);
        this.em = em;
    }
}
