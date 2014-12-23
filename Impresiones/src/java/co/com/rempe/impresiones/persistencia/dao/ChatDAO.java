/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.progredi.impresiones.persistencia.entidades.Chat;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */

public class ChatDAO extends GenericoDAO<Chat>  {
    
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChatDAO(EntityManager em){
        super(Chat.class);
        this.em=em;
    }
    
}
