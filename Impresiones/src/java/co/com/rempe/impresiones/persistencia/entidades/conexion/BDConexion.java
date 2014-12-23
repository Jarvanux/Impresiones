/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.entidades.conexion;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author hrey
 */
public final class BDConexion {

    private BDConexion() {
    }

    public static  EntityManager getEntityManager() throws Exception {
        return Persistence.createEntityManagerFactory("ImpresionesPU").createEntityManager();
    }
}
