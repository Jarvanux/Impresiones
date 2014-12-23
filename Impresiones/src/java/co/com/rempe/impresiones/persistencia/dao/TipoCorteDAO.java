/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.TipoCorte;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class TipoCorteDAO extends GenericoDAO<TipoCorte> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCorteDAO(EntityManager em) {
        super(TipoCorte.class);
        this.em = em;
    }

}
