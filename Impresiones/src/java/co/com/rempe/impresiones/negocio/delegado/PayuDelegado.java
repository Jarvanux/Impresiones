package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.BuscarEmoticones;
import co.com.rempe.impresiones.persistencia.dao.ChatDAO;
import co.com.rempe.impresiones.persistencia.dao.MensajeBuzonDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.MenajesBuzon;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
//Creado el 12/09/2014.
public class PayuDelegado {

    private static PayuDelegado instancia = new PayuDelegado();

    public PayuDelegado() {
    }

    public static PayuDelegado getInstancia() {
        return instancia;
    }   
}
