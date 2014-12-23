/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Pedido;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class PedidoDAO extends GenericoDAO<Pedido> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoDAO(EntityManager em) {
        super(Pedido.class);
        this.em = em;
    }
}
