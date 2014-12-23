/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.HistoricoPedido;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */

public class HistoricoPedidoDAO extends GenericoDAO<HistoricoPedido>{
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoPedidoDAO(EntityManager em) {
        super(HistoricoPedido.class);
        this.em=em;
    }
    
}
