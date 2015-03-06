/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.UtilOne;
import co.com.rempe.impresiones.persistencia.dao.DireccionesDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.DireccionesUsuarios;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhonjaider1000
 */
public class UsuarioDelegado {

    private static UsuarioDelegado instancia = new UsuarioDelegado();

    private UsuarioDelegado() {
    }

    public static UsuarioDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarUsuario(Usuarios usuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            UsuariosDAO usuariosDAO = new UsuariosDAO(em);
            usuario.setConectado(true);
            usuario.setFechaRegistro(new Date());
            usuario.setUltimaConexion(new Date());
            usuariosDAO.crear(usuario);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(usuario.getIdUsuario());
            respuesta.setMensaje("Registro Creado!.");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al insertar registro.");
        }
        return respuesta;
    }

    public Respuesta guardarUsuario(Usuarios usuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            UsuariosDAO usuariosDAO = new UsuariosDAO(em);
            usuario.setFechaRegistro(new Date());
            usuario.setEstado(2);
            usuario.setContrasena(UtilOne.md5(usuario.getCedula()));
            usuariosDAO.crear(usuario);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(usuario.getIdUsuario());
            respuesta.setMensaje("Usuario guardado, la información de ingreso se ha enviado vía correo electrónico.");
            System.out.println("Resultado envio clave: " + GestionCorreos.getInstancia().enviarContrasena(usuario).getMensaje());
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error a l guardar el usuario.");
        }
        return respuesta;
    }

    public Respuesta ingresar(String usuario, String clave) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.iniciarSesion(usuario, usuario, clave);
            if (usuarios != null) {
                usuarios.setContrasena("No subministrada por el sistema");
            }
            int codigo = (usuarios == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuarios);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(UsuarioDelegado.class.getName()).log(Level.SEVERE, null, e);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar datos solicitados.");
            return respuesta;
        }
    }

    public Usuarios consultarUsuario(long idUsuario) {
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
            return usuarios;
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la consulta del usuario por id.");
            return null;
        }
    }

    public Respuesta consultarUsuarioForResponse(long idUsuario) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
            int codigo = (usuarios == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuarios);
            respuesta.setMensaje("Se ha consultado el usuario correctamente.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar el usuario.");
            System.out.println("Se ha producido un error en la consulta del usuario por id.");
        }
        return respuesta;
    }

    public Respuesta buscarAsesores() {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuario = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuario = dao.buscarSoporte();
            int codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            if (codigo == 0) {
                usuario = dao.buscarSoporteRecurso2();
            }
            codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuario);
            respuesta.setMensaje("Asesor consultado satisfactoriamente");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar un asesor.");
        }
        return respuesta;
    }

    public Respuesta consultarSession(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        try {
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            int codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuario);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }

    public Usuarios consultaUsuarioLogeado(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    public Respuesta cerrarSesion(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        try {
            HttpSession session = request.getSession();
            Usuarios usuario = new Usuarios();
            usuario = (Usuarios) session.getAttribute("usuario");
            session.invalidate();
            cambiarEstadoConexion(usuario.getIdUsuario(), false);
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Sesión cerrada éxitosamente");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al cerrar la sesión");
        }
        return respuesta;
    }

    public Respuesta cambiarEstado(int idUsuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);

            em.getTransaction().begin();
            usuarios.setEstado(1);
            dao.editar(usuarios);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }

    public Respuesta cambiarEstadoConexion(int idUsuario, boolean conectado) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
            em.getTransaction().begin();
            usuarios.setSesionAdminCerrada(conectado);
            usuarios.setConectado(conectado);
            dao.editar(usuarios);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }

    public Usuarios seleccionarAsesor(List<Usuarios> lista) {
        Usuarios usuario = new Usuarios();

        for (int i = 0; i < lista.size(); i++) {
            usuario = lista.get(i);
        }
        return usuario;
    }

    public Respuesta cambiarEstadoAdmin(int idUsuario, int idEstado) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
            em.getTransaction().begin();
            if (idEstado != 3) {
                usuarios.setConectado(true);
            } else if (idEstado == 3) {
                usuarios.setConectado(false);
            }
            usuarios.setEstado(idEstado);
            dao.editar(usuarios);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha cambiado tú estado satisfactoriamente!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo cambiar tú estado.");
        }
        return respuesta;
    }

    public Respuesta actualizarDatos(Usuarios usuario) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuario2 = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuario2 = consultarUsuario(usuario.getIdUsuario());
            usuario2.setNombres(usuario.getNombres());
            usuario2.setApellidos(usuario.getNombres());
            usuario2.setEmail(usuario.getEmail());
            usuario2.setCedula(usuario.getCedula());
            usuario2.setCelular(usuario.getCelular());
            usuario2.setTelefono(usuario.getTelefono());
            usuario2.setCiudad(usuario.getCiudad());
            usuario2.setZona(usuario.getZona());
            usuario2.setFechaNacimiento(usuario.getFechaNacimiento());
            dao.editar(usuario2);
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se han actualizado tus datos!");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se han podido actualizar tus datos.");
        }
        return respuesta;
    }

    public Respuesta listarDirecciones(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            List<DireccionesUsuarios> list = null;
            if (usuario != null) {
                DireccionesDAO direccionesDAO = new DireccionesDAO(em);
                list = direccionesDAO.direcciones(usuario.getIdUsuario());
            }
            int codigo = (list.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(list);
            respuesta.setMensaje("Se han consultado las direcciones del usuario correctamente.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar direcciones del usuario.");
        }
        return respuesta;
    }

    public Respuesta guardarDireccion(DireccionesUsuarios direcciones) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            DireccionesDAO dao = new DireccionesDAO(em);
            if (direcciones != null && direcciones.getIdUsuario() > 0) {
                if (!(dao.consultarDireccion(direcciones.getDireccion()).size() > 0)) {
                    em.getTransaction().begin();
                    direcciones.setFechaRegistro(new Date());
                    dao.crear(direcciones);
                    em.getTransaction().commit();
                    respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
                    respuesta.setMensaje("Se ha guardado la dirección");
                } else {
                    respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
                    respuesta.setMensaje("La dirección ya se encuentra registrada.");
                }
            } else {
                respuesta.setCodigo(ECodigoRespuesta.VACIO.getCodigo());
                respuesta.setMensaje("No hay datos para guardar");
            }
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo guardar la dirección.");
        }
        return respuesta;
    }

    public Respuesta listarUsuariosPorROL(int rol) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            UsuariosDAO dao = new UsuariosDAO(em);
            List<Usuarios> list = dao.listarUsuariosPorROL(rol);
            int codigo = (list.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            res.setCodigo(codigo);
            res.setDatos(list);
            res.setMensaje("Se han consultado los usuarios satisfactoriamente.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo consultar los usuarios.");
        }
        return res;
    }

    public Respuesta bloquearUsuario(int idUsuario) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios u = consultarUsuario(idUsuario);
            u.setActivo(false);
            u.setConectado(false);
            UsuariosDAO dao = new UsuariosDAO(em);
            em.getTransaction().begin();
            dao.editar(u);
            em.getTransaction().commit();
            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setMensaje("Usuario bloqueado correctamente!.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo bloquear el usuario.");
        }
        return res;
    }

    public Respuesta activarUsuario(int idUsuario) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios u = consultarUsuario(idUsuario);
            u.setActivo(true);
            UsuariosDAO dao = new UsuariosDAO(em);
            em.getTransaction().begin();
            dao.editar(u);
            em.getTransaction().commit();
            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setMensaje("Usuario activado correctamente!.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo activar el usuario.");
        }
        return res;
    }

    public Respuesta disponibilidad(HttpServletRequest request) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            UsuariosDAO dao = new UsuariosDAO(em);
            em = BDConexion.getEntityManager();
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            if (usuario != null) {
                usuario = dao.consultarUsuarioPorID(usuario.getIdUsuario());
                if (usuario != null) {
                    res.setDatos(usuario.getActivo());
                }
            }
            int codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            res.setCodigo(codigo);
            res.setMensaje("Disponibilidad del usuario consultada.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("Se ha producido un error al consultar la disponibilidad del usuario.");
        }
        return res;
    }

    public Respuesta editarUsuario(Usuarios usuario) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        System.out.println("ID USUARIO: " + usuario);
        try {
            em = BDConexion.getEntityManager();
            UsuariosDAO dao = new UsuariosDAO(em);
            Usuarios usuario2 = null;
            if (usuario != null) {
                usuario2 = dao.consultarUsuarioPorID(usuario.getIdUsuario());
            }
            if (usuario != null && usuario2 != null) {
                usuario2.setIdRol(usuario.getIdRol());
                usuario2.setNombres(usuario.getNombres());
                usuario2.setApellidos(usuario.getApellidos());
                usuario2.setCedula(usuario.getCedula());
                usuario2.setCelular(usuario.getCelular());
                usuario2.setTelefono(usuario.getTelefono());
                usuario2.setCiudad(usuario.getCiudad());
                em.getTransaction().begin();
                dao.editar(usuario2);
                em.getTransaction().commit();
            }
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha editado el usuario.");
        } catch (Exception e) {
            System.err.println("Error al editar el usuario: " + e.getMessage());
//            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al editar el usuario.");
        }
        return respuesta;
    }

    public Respuesta filtroUsuarios(int rol,String filtro) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            UsuariosDAO dao = new UsuariosDAO(em);
            List<Usuarios> list = dao.filtrarUsuarios(rol, filtro);
            int codigo = (list.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            res.setCodigo(codigo);
            res.setDatos(list);
            res.setMensaje("Se han consultado los usuarios satisfactoriamente.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo consultar los usuarios.");
        }
        return res;
    }
}
