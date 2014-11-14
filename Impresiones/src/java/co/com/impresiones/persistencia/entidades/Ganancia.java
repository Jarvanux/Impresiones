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
@Table(name = "ganancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ganancia.findAll", query = "SELECT g FROM Ganancia g"),
    @NamedQuery(name = "Ganancia.findByIdGanancia", query = "SELECT g FROM Ganancia g WHERE g.idGanancia = :idGanancia"),
    @NamedQuery(name = "Ganancia.findByInicio", query = "SELECT g FROM Ganancia g WHERE g.inicio = :inicio"),
    @NamedQuery(name = "Ganancia.findByFin", query = "SELECT g FROM Ganancia g WHERE g.fin = :fin"),
    @NamedQuery(name = "Ganancia.findByPorcentaje", query = "SELECT g FROM Ganancia g WHERE g.porcentaje = :porcentaje"),
    @NamedQuery(name = "Ganancia.findByTipo", query = "SELECT g FROM Ganancia g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "Ganancia.findByIdTipoImpresion", query = "SELECT g FROM Ganancia g WHERE g.idTipoImpresion = :idTipoImpresion")})
public class Ganancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ganancia")
    private Integer idGanancia;
    @Column(name = "inicio")
    private Integer inicio;
    @Column(name = "fin")
    private Integer fin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "id_tipo_impresion")
    private Integer idTipoImpresion;

    public Ganancia() {
    }

    public Ganancia(Integer idGanancia) {
        this.idGanancia = idGanancia;
    }

    public Integer getIdGanancia() {
        return idGanancia;
    }

    public void setIdGanancia(Integer idGanancia) {
        this.idGanancia = idGanancia;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdTipoImpresion() {
        return idTipoImpresion;
    }

    public void setIdTipoImpresion(Integer idTipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGanancia != null ? idGanancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ganancia)) {
            return false;
        }
        Ganancia other = (Ganancia) object;
        if ((this.idGanancia == null && other.idGanancia != null) || (this.idGanancia != null && !this.idGanancia.equals(other.idGanancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.Ganancia[ idGanancia=" + idGanancia + " ]";
    }
    
}
