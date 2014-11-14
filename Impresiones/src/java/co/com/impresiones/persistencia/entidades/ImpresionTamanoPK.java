/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jhonjaider1000
 */
@Embeddable
public class ImpresionTamanoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_tamano")
    private int idTipoTamano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_impresion")
    private int idTipoImpresion;

    public ImpresionTamanoPK() {
    }

    public ImpresionTamanoPK(int idTipoTamano, int idTipoImpresion) {
        this.idTipoTamano = idTipoTamano;
        this.idTipoImpresion = idTipoImpresion;
    }

    public int getIdTipoTamano() {
        return idTipoTamano;
    }

    public void setIdTipoTamano(int idTipoTamano) {
        this.idTipoTamano = idTipoTamano;
    }

    public int getIdTipoImpresion() {
        return idTipoImpresion;
    }

    public void setIdTipoImpresion(int idTipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoTamano;
        hash += (int) idTipoImpresion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpresionTamanoPK)) {
            return false;
        }
        ImpresionTamanoPK other = (ImpresionTamanoPK) object;
        if (this.idTipoTamano != other.idTipoTamano) {
            return false;
        }
        if (this.idTipoImpresion != other.idTipoImpresion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.ImpresionTamanoPK[ idTipoTamano=" + idTipoTamano + ", idTipoImpresion=" + idTipoImpresion + " ]";
    }
    
}
