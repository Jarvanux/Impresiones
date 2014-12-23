/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "modo_impresion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModoImpresion.findAll", query = "SELECT m FROM ModoImpresion m"),
    @NamedQuery(name = "ModoImpresion.findByIdModoImpresion", query = "SELECT m FROM ModoImpresion m WHERE m.idModoImpresion = :idModoImpresion"),
    @NamedQuery(name = "ModoImpresion.findByModo", query = "SELECT m FROM ModoImpresion m WHERE m.modo = :modo")})
public class ModoImpresion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modo_impresion")
    private Integer idModoImpresion;
    @Column(name = "url_img")
    private String urlImg;
    @Size(max = 50)
    @Column(name = "modo")
    private String modo;

    public ModoImpresion() {
    }

    public ModoImpresion(Integer idModoImpresion) {
        this.idModoImpresion = idModoImpresion;
    }

    public Integer getIdModoImpresion() {
        return idModoImpresion;
    }

    public void setIdModoImpresion(Integer idModoImpresion) {
        this.idModoImpresion = idModoImpresion;
    }

    public String getModo() {
        return modo;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }    

    public void setModo(String modo) {
        this.modo = modo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModoImpresion != null ? idModoImpresion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModoImpresion)) {
            return false;
        }
        ModoImpresion other = (ModoImpresion) object;
        if ((this.idModoImpresion == null && other.idModoImpresion != null) || (this.idModoImpresion != null && !this.idModoImpresion.equals(other.idModoImpresion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.ModoImpresion[ idModoImpresion=" + idModoImpresion + " ]";
    }
    
}
