/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "servicios_adicionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosAdicionales.findAll", query = "SELECT s FROM ServiciosAdicionales s"),
    @NamedQuery(name = "ServiciosAdicionales.findByIdServicio", query = "SELECT s FROM ServiciosAdicionales s WHERE s.idServicio = :idServicio"),
    @NamedQuery(name = "ServiciosAdicionales.findByCodigo", query = "SELECT s FROM ServiciosAdicionales s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "ServiciosAdicionales.findByNombreServicio", query = "SELECT s FROM ServiciosAdicionales s WHERE s.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "ServiciosAdicionales.findByRangoInicio", query = "SELECT s FROM ServiciosAdicionales s WHERE s.rangoInicio = :rangoInicio"),
    @NamedQuery(name = "ServiciosAdicionales.findByRangoFin", query = "SELECT s FROM ServiciosAdicionales s WHERE s.rangoFin = :rangoFin"),
    @NamedQuery(name = "ServiciosAdicionales.findByValor", query = "SELECT s FROM ServiciosAdicionales s WHERE s.valor = :valor")})
public class ServiciosAdicionales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private Integer idServicio;
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 50)
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Column(name = "rango_inicio")
    private Integer rangoInicio;
    @Column(name = "rango_fin")
    private Integer rangoFin;
    @Column(name = "valor")
    private BigInteger valor;

    public ServiciosAdicionales() {
    }

    public ServiciosAdicionales(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getRangoInicio() {
        return rangoInicio;
    }

    public void setRangoInicio(Integer rangoInicio) {
        this.rangoInicio = rangoInicio;
    }

    public Integer getRangoFin() {
        return rangoFin;
    }

    public void setRangoFin(Integer rangoFin) {
        this.rangoFin = rangoFin;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosAdicionales)) {
            return false;
        }
        ServiciosAdicionales other = (ServiciosAdicionales) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.ServiciosAdicionales[ idServicio=" + idServicio + " ]";
    }
    
}
