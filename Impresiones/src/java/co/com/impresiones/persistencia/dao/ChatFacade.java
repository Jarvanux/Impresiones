/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Chat;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class ChatFacade extends AbstractFacade<Chat> implements ChatFacadeLocal {
    @PersistenceContext(unitName = "ImpresionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChatFacade() {
        super(Chat.class);
    }
    
}
