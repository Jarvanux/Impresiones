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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "CostosMantenimiento.findByValorCosto", query = "SELECT c FROM CostosMantenimiento c WHERE c.valorCosto = :valorCosto")})
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_costo")
    private double valorCosto;

    public CostosMantenimiento() {
    }

    public CostosMantenimiento(Integer idCostosMantenimiento) {
        this.idCostosMantenimiento = idCostosMantenimiento;
    }

    public CostosMantenimiento(Integer idCostosMantenimiento, double valorCosto) {
        this.idCostosMantenimiento = idCostosMantenimiento;
        this.valorCosto = valorCosto;
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

    public double getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(double valorCosto) {
        this.valorCosto = valorCosto;
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
