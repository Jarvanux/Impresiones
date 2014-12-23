package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.ModoImpresionDAO;
import co.com.rempe.impresiones.persistencia.entidades.ModoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class ModoImpresionDelegado {

    private static ModoImpresionDelegado instancia = new ModoImpresionDelegado();

    private ModoImpresionDelegado() {

    }

    public static ModoImpresionDelegado getInstancia() {
        return instancia;
    }

    public Respuesta listarModoImpresion() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ModoImpresionDAO dao = new ModoImpresionDAO(em);
            List<ModoImpresion> listaModoImpresion = dao.buscarTodos();
            int codigo = (listaModoImpresion.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaModoImpresion);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(ModoImpresionDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los modos de impresión");
            return respuesta;
        }
    }

}
