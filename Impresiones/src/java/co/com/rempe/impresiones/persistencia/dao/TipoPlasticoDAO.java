/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.TipoPlastico;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class TipoPlasticoDAO extends GenericoDAO<TipoPlastico> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPlasticoDAO(EntityManager em) {
        super(TipoPlastico.class);
        this.em = em;
    }
}
