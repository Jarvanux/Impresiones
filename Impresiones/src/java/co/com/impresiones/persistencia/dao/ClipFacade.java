/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Clip;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class ClipFacade extends AbstractFacade<Clip> implements ClipFacadeLocal {
    @PersistenceContext(unitName = "ImpresionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClipFacade() {
        super(Clip.class);
    }
    
}
