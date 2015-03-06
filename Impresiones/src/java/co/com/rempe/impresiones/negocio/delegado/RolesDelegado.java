package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.RolesDAO;
import co.com.rempe.impresiones.persistencia.entidades.Roles;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class RolesDelegado {

    private static RolesDelegado instancia = new RolesDelegado();

    public RolesDelegado() {
    }

    public static RolesDelegado getInstancia() {
        return instancia;
    }

    public Respuesta listarRoles() {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            RolesDAO dao = new RolesDAO(em);
            List<Roles> list = dao.buscarTodos();
            int codigo = (list.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            res.setCodigo(codigo);
            res.setDatos(list);
            res.setMensaje("Se ha consultado los roles.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("Error al consultar los roles.");
        }
        return res;
    }

}
