package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.ContentEditorDAO;
import co.com.rempe.impresiones.persistencia.entidades.Contenteditor;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class ContenteditorDelegado {

    private static ContenteditorDelegado instancia = new ContenteditorDelegado();

    public ContenteditorDelegado() {
    }

    public static ContenteditorDelegado getInstancia() {
        return instancia;
    }

//Método Insertar - Method insert.
    public Respuesta insertarContenteditor(String text) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ContentEditorDAO dao = new ContentEditorDAO(em);
            List<Contenteditor> list = dao.buscarTodos();
            if (!list.isEmpty()) {
                Contenteditor c = list.get(0);
                c.setText(text);
                dao.editar(c);
            } else {
                Contenteditor c = new Contenteditor();
                c.setText(text);
                dao.crear(c);
            }
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Código guardado.");
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo guardar el código.");
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("ContenteditorDelegado.insertar() >> " + ECodigoRespuesta.ERROR.getDescripcion());
            System.out.println("Error " + "ContenteditorDelegado.insertar() >>  " + ex.getMessage());
        }
        return respuesta;
    }

//Método Editar - Method edit.
    public Respuesta buscarContenteditor() {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            ContentEditorDAO dao = new ContentEditorDAO(em);
            List<Contenteditor> list = dao.buscarTodos();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            if (!list.isEmpty()) {
                Contenteditor c = list.get(0);
                respuesta.setDatos(c.getText());
            }else{
                respuesta.setDatos("// Su código JavaScript aquí.");
            }
            respuesta.setMensaje("Código Consultado");
        } catch (Exception ex) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("ContenteditorDelegado.editar() >> " + ECodigoRespuesta.ERROR.getDescripcion());
            System.out.println("Error " + "ContenteditorDelegado.editar() >>  " + ex.getMessage());
        }
        return respuesta;
    }

//Método Consultar por id - Method Find by Id.
}//End class
