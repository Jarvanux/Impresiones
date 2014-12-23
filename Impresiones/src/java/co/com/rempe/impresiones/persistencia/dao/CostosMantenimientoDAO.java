/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.CostosMantenimiento;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
public class CostosMantenimientoDAO extends GenericoDAO<CostosMantenimiento>{

    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CostosMantenimientoDAO(EntityManager em){
        super(CostosMantenimiento.class);
        this. em = em;
    }
    public CostosMantenimiento consultarCostosMantenimientoID(int idTipoColor){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from costos_mantenimiento where id_costos_mantenimiento = ?",CostosMantenimiento.class);
            q.setParameter(1, idTipoColor);
            return (CostosMantenimiento)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
