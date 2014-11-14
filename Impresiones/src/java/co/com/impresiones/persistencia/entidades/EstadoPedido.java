/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "estado_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPedido.findAll", query = "SELECT e FROM EstadoPedido e"),
    @NamedQuery(name = "EstadoPedido.findByIdEstadoPedido", query = "SELECT e FROM EstadoPedido e WHERE e.idEstadoPedido = :idEstadoPedido"),
    @NamedQuery(name = "EstadoPedido.findByEstado", query = "SELECT e FROM EstadoPedido e WHERE e.estado = :estado"),
    @NamedQuery(name = "EstadoPedido.findByDescripcion", query = "SELECT e FROM EstadoPedido e WHERE e.descripcion = :descripcion")})
public class EstadoPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_pedido")
    private Integer idEstadoPedido;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPedido", fetch = FetchType.LAZY)
    private List<HistoricoPedido> historicoPedidoList;

    public EstadoPedido() {
    }

    public EstadoPedido(Integer idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public Integer getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(Integer idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<HistoricoPedido> getHistoricoPedidoList() {
        return historicoPedidoList;
    }

    public void setHistoricoPedidoList(List<HistoricoPedido> historicoPedidoList) {
        this.historicoPedidoList = historicoPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPedido != null ? idEstadoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPedido)) {
            return false;
        }
        EstadoPedido other = (EstadoPedido) object;
        if ((this.idEstadoPedido == null && other.idEstadoPedido != null) || (this.idEstadoPedido != null && !this.idEstadoPedido.equals(other.idEstadoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.EstadoPedido[ idEstadoPedido=" + idEstadoPedido + " ]";
    }
    
}
