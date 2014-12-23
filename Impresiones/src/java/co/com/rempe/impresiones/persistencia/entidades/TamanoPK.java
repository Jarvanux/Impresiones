/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author hrey
 */
@Embeddable
public class TamanoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tamano")
    private int idTamano;
    @Basic(optional = false)
    @Column(name = "id_tipo_papel")
    private int idTipoPapel;
    @Basic(optional = false)
    @Column(name = "id_tipo_tamano")
    private int idTipoTamano;

    public TamanoPK() {
    }

    public TamanoPK(int idTamano, int idTipoPapel, int idTipoTamano) {
        this.idTamano = idTamano;
        this.idTipoPapel = idTipoPapel;
        this.idTipoTamano = idTipoTamano;
    }

    public int getIdTamano() {
        return idTamano;
    }

    public void setIdTamano(int idTamano) {
        this.idTamano = idTamano;
    }

    public int getIdTipoPapel() {
        return idTipoPapel;
    }

    public void setIdTipoPapel(int idTipoPapel) {
        this.idTipoPapel = idTipoPapel;
    }

    public int getIdTipoTamano() {
        return idTipoTamano;
    }

    public void setIdTipoTamano(int idTipoTamano) {
        this.idTipoTamano = idTipoTamano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTamano;
        hash += (int) idTipoPapel;
        hash += (int) idTipoTamano;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TamanoPK)) {
            return false;
        }
        TamanoPK other = (TamanoPK) object;
        if (this.idTamano != other.idTamano) {
            return false;
        }
        if (this.idTipoPapel != other.idTipoPapel) {
            return false;
        }
        if (this.idTipoTamano != other.idTipoTamano) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.TamanoPK[ idTamano=" + idTamano + ", idTipoPapel=" + idTipoPapel + ", idTipoTamano=" + idTipoTamano + " ]";
    }
    
}
