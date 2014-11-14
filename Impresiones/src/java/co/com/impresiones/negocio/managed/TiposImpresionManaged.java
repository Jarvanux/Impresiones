package co.com.impresiones.negocio.managed;

import co.com.impresiones.persistencia.dao.TipoImpresionFacadeLocal;
import co.com.impresiones.persistencia.entidades.TipoImpresion;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@RequestScoped
public class TiposImpresionManaged {
    
    public TiposImpresionManaged() {
    }

    private List<TipoImpresion> listaTipoImpresion;
    
    private int tipoImpresion;
    
    @EJB
    private TipoImpresionFacadeLocal tipoImpresionFL;
    
    @PostConstruct
    public void init(){
        this.listaTipoImpresion = this.tipoImpresionFL.listarTodo();
    }
    
    //Métodos set y get.
    public List<TipoImpresion> getListaTipoImpresion() {
        return listaTipoImpresion;
    }

    public void setListaTipoImpresion(List<TipoImpresion> listaTipoImpresion) {
        this.listaTipoImpresion = listaTipoImpresion;
    }

    public int getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(int tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    
}
