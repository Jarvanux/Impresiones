/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.EstadoPedido;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class EstadoPedidoDAO extends GenericoDAO<EstadoPedido> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPedidoDAO(EntityManager em) {
        super(EstadoPedido.class);
        this.em = em;
    }
}
