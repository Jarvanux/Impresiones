/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "clip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clip.findAll", query = "SELECT c FROM Clip c"),
    @NamedQuery(name = "Clip.findByIdClip", query = "SELECT c FROM Clip c WHERE c.idClip = :idClip"),
    @NamedQuery(name = "Clip.findByClip", query = "SELECT c FROM Clip c WHERE c.clip = :clip"),
    @NamedQuery(name = "Clip.findByRecuperacionInversion", query = "SELECT c FROM Clip c WHERE c.recuperacionInversion = :recuperacionInversion"),
    @NamedQuery(name = "Clip.findByCostoOperador", query = "SELECT c FROM Clip c WHERE c.costoOperador = :costoOperador"),
    @NamedQuery(name = "Clip.findByTipo", query = "SELECT c FROM Clip c WHERE c.tipo = :tipo")})
public class Clip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clip")
    private Integer idClip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "clip")
    private BigDecimal clip;
    @Column(name = "recuperacion_inversion")
    private BigDecimal recuperacionInversion;
    @Column(name = "costo_operador")
    private BigDecimal costoOperador;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;

    public Clip() {
    }

    public Clip(Integer idClip) {
        this.idClip = idClip;
    }

    public Integer getIdClip() {
        return idClip;
    }

    public void setIdClip(Integer idClip) {
        this.idClip = idClip;
    }

    public BigDecimal getClip() {
        return clip;
    }

    public void setClip(BigDecimal clip) {
        this.clip = clip;
    }

    public BigDecimal getRecuperacionInversion() {
        return recuperacionInversion;
    }

    public void setRecuperacionInversion(BigDecimal recuperacionInversion) {
        this.recuperacionInversion = recuperacionInversion;
    }

    public BigDecimal getCostoOperador() {
        return costoOperador;
    }

    public void setCostoOperador(BigDecimal costoOperador) {
        this.costoOperador = costoOperador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClip != null ? idClip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clip)) {
            return false;
        }
        Clip other = (Clip) object;
        if ((this.idClip == null && other.idClip != null) || (this.idClip != null && !this.idClip.equals(other.idClip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.Clip[ idClip=" + idClip + " ]";
    }
    
}
