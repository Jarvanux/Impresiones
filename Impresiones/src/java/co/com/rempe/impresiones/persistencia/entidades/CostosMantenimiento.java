/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "costos_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostosMantenimiento.findAll", query = "SELECT c FROM CostosMantenimiento c"),
    @NamedQuery(name = "CostosMantenimiento.findByIdCostosMantenimiento", query = "SELECT c FROM CostosMantenimiento c WHERE c.idCostosMantenimiento = :idCostosMantenimiento"),
    @NamedQuery(name = "CostosMantenimiento.findByDescripcion", query = "SELECT c FROM CostosMantenimiento c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CostosMantenimiento.findByValorCosto", query = "SELECT c FROM CostosMantenimiento c WHERE c.valorCosto = :valorCosto"),
    @NamedQuery(name = "CostosMantenimiento.findByClipColor", query = "SELECT c FROM CostosMantenimiento c WHERE c.clipColor = :clipColor"),
    @NamedQuery(name = "CostosMantenimiento.findByRecuInversion", query = "SELECT c FROM CostosMantenimiento c WHERE c.recuInversion = :recuInversion"),
    @NamedQuery(name = "CostosMantenimiento.findByOperadores", query = "SELECT c FROM CostosMantenimiento c WHERE c.operadores = :operadores"),
    @NamedQuery(name = "CostosMantenimiento.findByUltimaActualizacion", query = "SELECT c FROM CostosMantenimiento c WHERE c.ultimaActualizacion = :ultimaActualizacion")})
public class CostosMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_costos_mantenimiento")
    private Integer idCostosMantenimiento;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_costo")
    private Double valorCosto;
    @Column(name = "clip_color")
    private Double clipColor;
    @Column(name = "recu_inversion")
    private Double recuInversion;
    @Column(name = "operadores")
    private Double operadores;
    @Column(name = "ultima_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;

    public CostosMantenimiento() {
    }

    public CostosMantenimiento(Integer idCostosMantenimiento) {
        this.idCostosMantenimiento = idCostosMantenimiento;
    }

    public Integer getIdCostosMantenimiento() {
        return idCostosMantenimiento;
    }

    public void setIdCostosMantenimiento(Integer idCostosMantenimiento) {
        this.idCostosMantenimiento = idCostosMantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(Double valorCosto) {
        this.valorCosto = valorCosto;
    }

    public Double getClipColor() {
        return clipColor;
    }

    public void setClipColor(Double clipColor) {
        this.clipColor = clipColor;
    }

    public Double getRecuInversion() {
        return recuInversion;
    }

    public void setRecuInversion(Double recuInversion) {
        this.recuInversion = recuInversion;
    }

    public Double getOperadores() {
        return operadores;
    }

    public void setOperadores(Double operadores) {
        this.operadores = operadores;
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCostosMantenimiento != null ? idCostosMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostosMantenimiento)) {
            return false;
        }
        CostosMantenimiento other = (CostosMantenimiento) object;
        if ((this.idCostosMantenimiento == null && other.idCostosMantenimiento != null) || (this.idCostosMantenimiento != null && !this.idCostosMantenimiento.equals(other.idCostosMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.CostosMantenimiento[ idCostosMantenimiento=" + idCostosMantenimiento + " ]";
    }
    
}
