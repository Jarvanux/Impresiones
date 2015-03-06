/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.CalcularPaginas;
import co.com.rempe.impresiones.negocio.utilerias.DatosRespuesta;
import co.com.rempe.impresiones.negocio.utilerias.Utilerias;
import co.com.rempe.impresiones.negocio.utilerias.ValorImpresion;
import co.com.rempe.impresiones.persistencia.dao.ColorDAO;
import co.com.rempe.impresiones.persistencia.dao.CostosMantenimientoDAO;
import co.com.rempe.impresiones.persistencia.dao.ImpresionLaserDAO;
import co.com.rempe.impresiones.persistencia.dao.ModoImpresionDAO;
import co.com.rempe.impresiones.persistencia.dao.PorcentajeGananciaDAO;
import co.com.rempe.impresiones.persistencia.dao.RelacionesPapelDAO;
import co.com.rempe.impresiones.persistencia.dao.ServiciosAdicionalesDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoCorteDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoPapelDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoPlasticoDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoTamanoDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.Color;
import co.com.rempe.impresiones.persistencia.entidades.CostosMantenimiento;
import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import co.com.rempe.impresiones.persistencia.entidades.ModoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.PorcentajeGanacia;
import co.com.rempe.impresiones.persistencia.entidades.RelacionesPapel;
import co.com.rempe.impresiones.persistencia.entidades.ServiciosAdicionales;
import co.com.rempe.impresiones.persistencia.entidades.TipoCorte;
import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import co.com.rempe.impresiones.persistencia.entidades.TipoPlastico;
import co.com.rempe.impresiones.persistencia.entidades.TipoTamano;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jhonjaider1000
 */
public class ImpresionDelegado {

    private static ImpresionDelegado instancia = new ImpresionDelegado();

    public ImpresionDelegado() {
    }

    public static ImpresionDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarPrimerForm(ImpresionLaser impresionLaser) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
//            System.out.println("Tipo Color: " + impresionLaser.getTipoColor());
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
            dao.crear(impresionLaser);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Primeros parámetros de la impresión guarddos.");
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(ImpresionDelegado.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al insertar.");
            return respuesta;
        }
    }

    public Respuesta consultarModoImpre(int idModo) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ModoImpresion objetoRespuesta = new ModoImpresion();
            ModoImpresionDAO dao = new ModoImpresionDAO(em);
            objetoRespuesta = dao.consultarModoImpresion(idModo);
            int codigo = (objetoRespuesta == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(objetoRespuesta);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(ImpresionDelegado.class.getName()).log(Level.SEVERE, null, e);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los datos solicitados.");
            return respuesta;
        }
    }

    public Respuesta colores() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ColorDAO dao = new ColorDAO(em);
            List<Color> listaColores = dao.buscarTodos();
            int codigo = (listaColores.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaColores);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tamaños de impresión");
            return respuesta;
        }
    }

    public Respuesta consultarValorServicio(int codigo, int numData) {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ServiciosAdicionales objetoRespuesta = new ServiciosAdicionales();
            ServiciosAdicionalesDAO dao = new ServiciosAdicionalesDAO(em);
            objetoRespuesta = dao.consultarValores(codigo, numData);
            int codigo2 = (objetoRespuesta == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo2);
            respuesta.setDatos(objetoRespuesta);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");
            return respuesta;
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo consultar los datos. ERROR: " + e.getMessage());
            return respuesta;
        }
    }

    public Respuesta tipoPlastico() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoPlasticoDAO dao = new TipoPlasticoDAO(em);
            List<TipoPlastico> listaPlasticos = dao.buscarTodos();
            int codigo = (listaPlasticos.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setDatos(listaPlasticos);
            respuesta.setCodigo(codigo);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar datos solicitados.");
            return respuesta;
        }
    }

    public Respuesta tipoCorte() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoCorteDAO dao = new TipoCorteDAO(em);
            List<TipoCorte> lista = dao.buscarTodos();
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(lista);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tamaños de impresión");
            return respuesta;
        }
    }

    public Respuesta calculaNumPaginas(String cadena) {
        Respuesta respuesta = new Respuesta();
//        System.out.println(cadena);
        try {
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            String resultado = CalcularPaginas.controlCalculo(cadena);
            String[] arrayTemp = resultado.split("/");
            DatosRespuesta datos = new DatosRespuesta();
            if (resultado.contains("|") || (Integer.parseInt(arrayTemp[0])) == 0) {
                respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
                datos.setString(arrayTemp[2]);
                respuesta.setDatos(datos);
                if (arrayTemp[1].startsWith("|")) {
                    respuesta.setMensaje(arrayTemp[1].substring(1));
                } else {
                    respuesta.setMensaje(arrayTemp[1]);
                }
            } else {
                respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
                datos.setEntero(Integer.parseInt(arrayTemp[0]));
                datos.setString(arrayTemp[2]); //Vendría siendo la cadena corregida.
                respuesta.setDatos(datos);
                respuesta.setMensaje("Se ha calculado exitosamente el número de páginas.");
            }
            return respuesta;

        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error directamente desde el servlet, contacte el desarrollador.");
            return respuesta;
        }
    }

    //Calcular valor de una impresión.
    public Respuesta calcularValorImpre(int tipoColor, int idModo, int tamano, int papel, int numHojas) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            //Cosultamos el modo de impresión que ha seleccionado el usuario.
//            ModoImpresion modoImpresion = new ModoImpresion();
//            ModoImpresionDAO modoImpresionDAO = new ModoImpresionDAO(em);
//            modoImpresion = modoImpresionDAO.consultarModoImpresion(idModo);
            //Fin consulta modo impresión.

            //Consultamos el tamaño seleccionado.
            TipoTamano tipoTamano = new TipoTamano();
            TipoTamanoDAO tipoTamanoDAO = new TipoTamanoDAO(em);
            tipoTamano = tipoTamanoDAO.consultarTipoTamano(tamano);
            //Fin consulta tamaño.

//            System.out.println("TIPO tamaño: " + tipoTamano.getTipoTamano());
            //Consultamos el papel.
            TipoPapel tipoPapel = new TipoPapel();
            TipoPapelDAO tipoPapelDAO = new TipoPapelDAO(em);
            tipoPapel = tipoPapelDAO.consultarTipoPapelPorID(papel);
            //Fin consulta papel.

//            System.out.println("Tipo papel: " + tipoPapel.getPapel());
//
            //Consultamos los costos de mantenimiento según el tipo de color seleccionado.
            CostosMantenimiento costosMantenimiento = new CostosMantenimiento();
            CostosMantenimientoDAO costosMantenimientoDAO = new CostosMantenimientoDAO(em);
            costosMantenimiento = costosMantenimientoDAO.consultarCostosMantenimientoID(tipoColor);
            //Fin consulta costos mantenimiento.

            //Una vez consultados los parámetros necesarios para hacer el calculo 
            //vamos a realizar algunas condiciones.            
            double precioPliego = tipoPapel.getPrecioPliego();
            double numeroHojas = tipoTamano.getNumeroHojas();

//            System.out.println("Precio pliego: " + precioPliego);
//            System.out.println("Número hojas: " + precioPliego);
            //Si tipoColor = 0 la impresión es a B/N, si es 1 es a Color
            double valorFinal = precioPliego / numeroHojas;

//            System.out.println("Valor final: " + valorFinal);
            //precioPliego / numero de hojas
//            System.out.println(valorFinal);
            //Multiplicamos El costo de mantenimiento * el tipo de papel + el valor de la impresión.
            double costoMantent = costosMantenimiento.getValorCosto();
//            System.out.println(costoMantent); //precioPliego / numero de hojas * costo mantinimiento.
            //Una vez multiplicado el costo de mantenimiento * el tipo del papel, es hora de sumar.
            costoMantent *= tipoTamano.getCostoImpresion(); //precioPliego / numero de hojas

//            System.out.println(costoMantent);
            valorFinal = (valorFinal + costoMantent);
//            System.out.println(valorFinal);
            ValorImpresion valorImpresion = new ValorImpresion();

            PorcentajeGanacia porcentajeGanancia = new PorcentajeGanacia();
            PorcentajeGananciaDAO porcentajeGananciaDAO = new PorcentajeGananciaDAO(em);
            porcentajeGanancia = porcentajeGananciaDAO.consultarGanancia(numHojas, tipoColor);

            valorFinal *= porcentajeGanancia.getPorcentaje() / 100;
            //precioPliego / numero de hojas
            valorImpresion.setValorImpresion(Utilerias.redondearDecimal(valorFinal));

//            ValorCosto * %Ganancia / 100.
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(valorImpresion);
            respuesta.setMensaje("Valor de impresión calculado existosamente!.");
            return respuesta;
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al calcular el valor de la impresión.");
            return respuesta;
        }
    }
    //Fin calcular valor de una impresión.

    public Respuesta asigDataImpre() {
        Respuesta respuesta = new Respuesta();
        try {
            DatosRespuesta datos = new DatosRespuesta();
            datos.setEnteroGrande(System.currentTimeMillis());
            datos.setFecha(new Date());
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(datos);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }

    public Respuesta guardarImpresionLaser(ImpresionLaser data, long idUSuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
//            System.out.println("Tipo Color: " + data.getTipoColor());
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            //Fijamos el usuario para saber que está comprando.
            Usuarios usuario = UsuarioDelegado.getInstancia().consultarUsuario(idUSuario);
//            System.out.println("Id Usuario Recibido: " + idUSuario);
            if (usuario != null) {
//                System.out.println("Consultado.");
                usuario.setComprando(true);
                UsuariosDAO daoUser = new UsuariosDAO(em);
                daoUser.editar(usuario);
                ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
                Date hora = new Date();
//                System.out.println(hora);
                data.setFecha(hora);
                data.setIdUsuario(idUSuario); //Le pasamos el id del usuario logeado.
                dao.crear(data);//Y finalmente guardamos la impresión realizada
                em.getTransaction().commit();
                respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
                respuesta.setMensaje("Impresión guardada");
            } else {
                respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
                respuesta.setMensaje("El usuario no ha sido consultado.");
//                System.err.println("No consultado.");
            }
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(ImpresionDelegado.class.getName()).log(Level.SEVERE, null, e);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al insertar.");
            return respuesta;
        }
    }

    public Respuesta listarImpresionesLaser(long idUsuario, int tipoImpresion) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
            List<ImpresionLaser> lista = dao.listarImpresionesLaser(idUsuario, tipoImpresion);
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            //Pasamos la lista que vamos a retornar.
            respuesta.setDatos(lista);
            respuesta.setMensaje("Se ha conseguido el listado de las impresiones laser.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo conseguir el listado de las impresiones laser.");
        }
        //Retornamos el resultado.
        return respuesta;
    }

    public Respuesta filtrarImpresionLaser(long idUsuario, String filtro, int tipoImpresion) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
            List<ImpresionLaser> lista = dao.filtrarImpresionesLaser(idUsuario, filtro);
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            //Pasamos la lista que vamos a retornar.
            respuesta.setDatos(lista);
            respuesta.setMensaje("Se ha conseguido el listado de las impresiones laser.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo conseguir el listado de las impresiones laser.");
        }
        return respuesta;
    }

    public Respuesta ultimaImpresionLaser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Respuesta ultimaImpresionLaser(Usuarios usuario) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            ImpresionLaserDAO dao = new ImpresionLaserDAO(em);
            if (usuario.getComprando()) {
                ImpresionLaser impresion = dao.ultimaImpresionUsuario(usuario.getIdUsuario());
                int codigo = (impresion == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
                respuesta.setCodigo(codigo);
                respuesta.setDatos(impresion);
                respuesta.setMensaje("Se ha obtenido la última impresión del cliente.");
            } else {
                respuesta.setCodigo(ECodigoRespuesta.VACIO.getCodigo());
                respuesta.setMensaje("El cliente no está comprando, petición no válida.");
            }
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al obtener la última impresión del cliente.");
        }
        return respuesta;
    }

    public Respuesta consultarPrecios(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            CostosMantenimientoDAO dao = new CostosMantenimientoDAO(em);
            //Listamos los costos de mantenimiento.
            List<CostosMantenimiento> costos = dao.buscarTodos();

            //Listamos los costos internos de impresión laser.
            TipoPapelDAO daoTp = new TipoPapelDAO(em);
            List<TipoPapel> listaTp = daoTp.buscarTodos();

            //Listamos los tamaños.
            TipoTamanoDAO daoTt = new TipoTamanoDAO(em);
            List<TipoTamano> listaTt = daoTt.buscarTodos();

            //Listammos los porcentajes de ganancia para color.
            PorcentajeGananciaDAO daoG = new PorcentajeGananciaDAO(em);
            List<PorcentajeGanacia> listGC = daoG.gananciasColor(1);

            //Listamos los porcentajes de ganancia para bn.
            List<PorcentajeGanacia> listGBN = daoG.gananciasColor(0);

            List<Object> listaReturn = new ArrayList<Object>();
            listaReturn.add(costos);
            listaReturn.add(listaTp);
            listaReturn.add(listaTt);
            listaReturn.add(listGC);
            listaReturn.add(listGBN);

            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Precios consultados satisfactoriamente");
            respuesta.setDatos(listaReturn);
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar precios.");
        }
        return respuesta;
    }

    public Respuesta actualizarCostosMantenimiento(CostosMantenimiento dataBn, CostosMantenimiento dataColor) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            CostosMantenimientoDAO dao = new CostosMantenimientoDAO(em);
            CostosMantenimiento costo = dao.consultarCostosMantenimientoID(dataBn.getIdCostosMantenimiento());
//            System.err.println(costo);
            costo.setClipColor(dataBn.getClipColor());
            costo.setOperadores(dataBn.getOperadores());
            costo.setRecuInversion(dataBn.getRecuInversion());
            costo.setValorCosto(dataBn.getValorCosto());
            costo.setUltimaActualizacion(new Date());

            em.getTransaction().begin();
            dao.editar(costo);
            em.getTransaction().commit();

            costo = dao.consultarCostosMantenimientoID(dataColor.getIdCostosMantenimiento());
            costo.setClipColor(dataColor.getClipColor());
            costo.setOperadores(dataColor.getOperadores());
            costo.setRecuInversion(dataColor.getRecuInversion());
            costo.setValorCosto(dataColor.getValorCosto());
            costo.setUltimaActualizacion(new Date());

            em.getTransaction().begin();
            dao.editar(costo);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(new Date());
            respuesta.setMensaje("Se han guardado los cambios realizados.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al guardar los cambios realizados.");
            em.getTransaction().rollback();
        }
        return respuesta;
    }

    public Respuesta actualizarNombrePapel(int idPapel, String nuevoNombre) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            TipoPapelDAO dao = new TipoPapelDAO(em);
            TipoPapel tipoPapel = dao.consultarTipoPapelPorID(idPapel);
            tipoPapel.setPapel(nuevoNombre);
            dao.editar(tipoPapel);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha editado el nombre del papel satisfactoriamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al actualizar el nombre del papel.");
        }
        return respuesta;
    }

    public Respuesta actualizarPrecioPapel(int idPapel, double precioPapel) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            TipoPapelDAO dao = new TipoPapelDAO(em);
            TipoPapel tipoPapel = dao.consultarTipoPapelPorID(idPapel);
            tipoPapel.setPrecioPliego(precioPapel);
            dao.editar(tipoPapel);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha editado el precio del papel satisfactoriamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al actualizar el precio del papel.");
        }
        return respuesta;
    }

    public Respuesta actualizarPorcentajeGanancia(PorcentajeGanacia obj) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            PorcentajeGananciaDAO dao = new PorcentajeGananciaDAO(em);
            em.getTransaction().begin();
            dao.editar(obj);
//            System.err.println("OBJECT RECIVED: " + obj.getIdPorcentaje() + " " + obj.getInicio());
            em.getTransaction().commit();
            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setMensaje("Porcentaje de ganancia actualizado correctamente.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo actualizar el porcentaje de ganancia.");
        }
        return res;
    }

    public Respuesta eliminarTipoPapel(int id) {
//        System.out.println("ID LLEGADO: " + id);
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            TipoPapel tp = new TipoPapel();
            TipoPapelDAO dao = new TipoPapelDAO(em);
            tp = dao.consultarTipoPapelPorID(id);
            em.getTransaction().begin();
            dao.eliminar(tp);
            em.getTransaction().commit();
            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setMensaje("Se han elminiado correctamente los tipo de papel seleccionados.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("No se pudo eliminar los tipo de papel seleccionados.");
        }
        return res;
    }

    public Respuesta agregarTipoPapel(TipoPapel obj) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            TipoPapelDAO dao = new TipoPapelDAO(em);
            dao.crear(obj);
            em.getTransaction().commit();
            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setMensaje("Tipo de papel agregado correctamente!.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("Se ha producido un error al agregar el tipo de papel.");
        }
        return res;
    }

    public Respuesta consultarValoresImpresion(int idTipoPapel, int tipoColor, int idTipoTamano, int numPag) {
        Respuesta res = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            List<Object> listaExport = new ArrayList<Object>();
            //Obtenemos el tipo de papel, para saber cuanto cuesta el pliego.
            TipoPapelDAO dao = new TipoPapelDAO(em);
            TipoPapel tipo = dao.consultarTipoPapelPorID(idTipoPapel);
            listaExport.add(tipo);
            //Agregamos los primeros datos consultados.

//            //Obtenemos los valores de costo de mantenimiento.
            CostosMantenimientoDAO dao2 = new CostosMantenimientoDAO(em);
            CostosMantenimiento cst = dao2.consultarCostosMantenimientoID((tipoColor + 1));
//            //Una vez consultado vamos a agregarlo a la lista igualmente.            
            listaExport.add(cst);

            //Consultamos los tipo de tamaño.
            TipoTamanoDAO dao3 = new TipoTamanoDAO(em);
            TipoTamano tp = dao3.consultarTipoTamano(idTipoTamano);
            listaExport.add(tp);

            //Consultamos el porcentaje.
            PorcentajeGananciaDAO dao4 = new PorcentajeGananciaDAO(em);
            PorcentajeGanacia prc = new PorcentajeGanacia();
            prc = dao4.consultarGanancia(numPag, tipoColor);
            listaExport.add(prc);

            res.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            res.setDatos(listaExport);
            res.setMensaje("Valores de impresión consultados satisfactoriamente.");
        } catch (Exception e) {
            res.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            res.setMensaje("Error al consultar los valores de impresión");
        }
        return res;
    }

    public Respuesta crearRelacion(HttpServletRequest request) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            RelacionesPapel obj = null;
            String papelesSelect = request.getParameter("papelesSeleccionados");
            String tpImpresion = request.getParameter("tiposImpresion");
            String mdImpresion = request.getParameter("modosImpresion");
            String tmImpresion = request.getParameter("tamanosImpresion");

            String[] selectPapers = papelesSelect.split(",");
            String[] typesImpresion = tpImpresion.split(",");
            String[] modesImpresion = mdImpresion.split(",");
            String[] sizesImpresion = tmImpresion.split(",");
            RelacionesPapelDAO dao = new RelacionesPapelDAO(em);
            
            em.getTransaction().begin();
            for (int i = 0; i < selectPapers.length; i++) {
                //Inicialmmente quitamos todas las relaciones que tiene el papel.\
                dao.eliminarRelacionesPorId(Integer.parseInt(selectPapers[i]));
                //Creamos las relaciones con los tipos de impresión de ese respectivo papel.
                for (int j = 0; j < typesImpresion.length; j++) {
                    obj = new RelacionesPapel();
                    obj.setTipoPapel(Integer.parseInt(selectPapers[i]));
                    obj.setTipoImpresion(Integer.parseInt(typesImpresion[i]));                    
                    dao.crear(obj);                    
                }

                //Creamos las relaciones con los modo de impresión para el respectivo papel.
                for (int j = 0; j < modesImpresion.length; j++) {
                    obj = new RelacionesPapel();
                    obj.setTipoPapel(Integer.parseInt(selectPapers[i]));
                    obj.setModoImpresion(Integer.parseInt(modesImpresion[i]));                    
                    dao.crear(obj);                   
                }

                //Creamos las relaciones con los tamanos de papel para el respectivo papel.
                for (int j = 0; j < sizesImpresion.length; j++) {
                    obj = new RelacionesPapel();
                    obj.setTipoPapel(Integer.parseInt(selectPapers[i]));
                    obj.setTamanoPapel(Integer.parseInt(sizesImpresion[i]));                    
                    dao.crear(obj);                    
                }
            }
            
            em.getTransaction().commit();

            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se han guardado las configuraciones realizadas.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo guardar las configuraciones realizadas.");
            em.getTransaction().rollback();
            System.err.println("ERROR: "+e);
        }
        return respuesta;
    }

    public Respuesta consultarRelaciones(int idPapel) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            //Creamos lista de tipo Object, que almacenará nuestra respuesta con todas las consultas que
            //realizamos a continuación.
            List<Object> listExport = new ArrayList<Object>();
            RelacionesPapelDAO dao = new RelacionesPapelDAO(em);
            List<RelacionesPapel> listTiposImpresion = dao.listTiposImpresion(idPapel);
            List<RelacionesPapel> listModoImpresion = dao.listModosImpresion(idPapel);
            List<RelacionesPapel> listTamanoImpresion = dao.listTamanosImpresion(idPapel);
            
            //Agregamos las listas a la lista de tipo Object.
            listExport.add(listTiposImpresion);
            listExport.add(listModoImpresion);
            listExport.add(listTamanoImpresion);
            
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(listExport);
            respuesta.setMensaje("Se han consultado la configuración del papel correctamente.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar las configuraciones del papel.");
        }
        return respuesta;
    }

}
