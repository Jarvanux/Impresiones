/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.PorcentajeGanacia;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
public class PorcentajeGananciaDAO extends GenericoDAO<PorcentajeGanacia>{

    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PorcentajeGananciaDAO(EntityManager em) {
        super(PorcentajeGanacia.class);
        this.em = em;
    }
    
    public PorcentajeGanacia consultarGanancia(int rango, int tipoColor){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from porcentaje_ganacia where inicio <= ? and fin >= ? and tipo_color = ?", PorcentajeGanacia.class);
            q.setParameter(1, rango);
            q.setParameter(2, rango);
            q.setParameter(3, tipoColor);
            return (PorcentajeGanacia)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    
    
    
}
