/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.progredi.impresiones.persistencia.entidades.Clip;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.EnvioCorreo;
import co.com.rempe.impresiones.negocio.utilerias.UtilOne;
import co.com.rempe.impresiones.persistencia.dao.ClipDAO;
import co.com.rempe.impresiones.persistencia.dao.InformacionEmpresaDAO;
import co.com.rempe.impresiones.persistencia.entidades.InformacionEmpresa;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

/**
 *
 * @author jhonjaider1000
 */
public class GestionCorreos {

    private static GestionCorreos instancia = new GestionCorreos();

    public GestionCorreos() {
    }

    public static GestionCorreos getInstancia() {
        return instancia;
    }

    public Respuesta enviarContrasena(Usuarios user) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        InformacionEmpresa informacion = null;
        try {
            em = BDConexion.getEntityManager();
            //Consultando informanción empresa.            
            InformacionEmpresaDAO informacionFL = new InformacionEmpresaDAO(em);
            informacion = informacionFL.buscarTodos().get(0);

            try {
                if (UtilOne.validarConexion()) {
                    EnvioCorreo mail = new EnvioCorreo();
                    String claveNueva = user.getCedula();
                    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
                    String fechaGeneracion = df.format(new Date());
                    String mensaje = ""
                            + "<div style=\"font-size: 14px; color: #444444; font-family: sans-serif; \">"
                            + "Cordial saludo! se ha creado un perfil nuevo en el sistema <b><a href='" + informacion.getUrlSistema() + "/login.html" + "' target='_blank'>Impresiones a Domicilio</a></b>, "
                            + "para su administración, por lo tanto se ha generado una contrasena con la cual podrá ingresar, "
                            + "cuando lo haga se recomienda cambiar la misma de inmediato por seguridad."
                            + "<br/>"
                            + "<br/>"
                            + "<b><h4 style='font-size: 16px; font-family: sans-serif;'>Información ingreso<h4></b>"
                            + "<b>Correo: </b>" + user.getEmail() + ".<br/>"
                            + "<b>Contraseña nueva</b>: " + claveNueva + ".<br/><br/><hr/>"
                            + "<b>Fecha de generación:</b> " + fechaGeneracion + "."
                            + "</div>";

                    String asunto = "Impresiones a domicilio - Información de cuenta registrada.";
                    String tituloContenido = "<H1>Información de cuenta registrada</H1>";
                    String correo = user.getEmail();
                    mail.envioCorreoSimple(tituloContenido, correo, informacion.getCorreo(), informacion.getClaveCorreo(), asunto, mensaje);

                } else {
                    respuesta.setCodigo(ECodigoRespuesta.VACIO.getCodigo());
                    respuesta.setMensaje("Verifique la conexión a internet, no se ha podido enviar la contrasena para el usuario creado.");
                }
            } catch (Exception e) {
                respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
                respuesta.setMensaje("Se ha producido un error al enviar la clave por correo eletrónico.");
            }
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha enviado correctamente la clave al correo electrónico del usuario.");
        } catch (Exception ex) {
            Logger.getLogger(ChatDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al enviar la calve al correo eléctronico.");
        }
        return respuesta;
    }
}
