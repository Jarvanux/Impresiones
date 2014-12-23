/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.ServiciosAdicionales;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
public class ServiciosAdicionalesDAO extends GenericoDAO<ServiciosAdicionales>{
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    public ServiciosAdicionalesDAO(EntityManager em){
        super(ServiciosAdicionales.class);
        this.em = em;
    }
    
    public ServiciosAdicionales consultarValores(int codigo, int numData){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from servicios_adicionales where codigo = ? and rango_inicio <= ? and rango_fin >= ?", ServiciosAdicionales.class);
            q.setParameter(1, codigo);
            q.setParameter(2, numData);
            q.setParameter(3, numData);
            return (ServiciosAdicionales)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }    
}
