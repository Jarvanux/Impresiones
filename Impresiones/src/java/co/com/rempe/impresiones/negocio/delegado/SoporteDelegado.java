/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.UsuarioDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.Usuario;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class SoporteDelegado {
    private static SoporteDelegado instancia = new SoporteDelegado();

    public SoporteDelegado() {
    }

    public static SoporteDelegado getInstancia() {
        return instancia;
    }
    public Respuesta soporteDisponible(){
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            List<Usuarios> lista = dao.soporteConectado();
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(lista);
            respuesta.setMensaje("Consulta realizada exitosamente!.");            
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar usuarios de soporte");
        }
        return respuesta;
    }
}
