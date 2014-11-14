/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "historico_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoPedido.findAll", query = "SELECT h FROM HistoricoPedido h"),
    @NamedQuery(name = "HistoricoPedido.findByIdHistoricoPedido", query = "SELECT h FROM HistoricoPedido h WHERE h.idHistoricoPedido = :idHistoricoPedido"),
    @NamedQuery(name = "HistoricoPedido.findByIdUsuarioEmpleado", query = "SELECT h FROM HistoricoPedido h WHERE h.idUsuarioEmpleado = :idUsuarioEmpleado"),
    @NamedQuery(name = "HistoricoPedido.findByFecha", query = "SELECT h FROM HistoricoPedido h WHERE h.fecha = :fecha")})
public class HistoricoPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_pedido")
    private Integer idHistoricoPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario_empleado")
    private int idUsuarioEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido idPedido;
    @JoinColumn(name = "id_estado_pedido", referencedColumnName = "id_estado_pedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoPedido idEstadoPedido;

    public HistoricoPedido() {
    }

    public HistoricoPedido(Integer idHistoricoPedido) {
        this.idHistoricoPedido = idHistoricoPedido;
    }

    public HistoricoPedido(Integer idHistoricoPedido, int idUsuarioEmpleado, Date fecha) {
        this.idHistoricoPedido = idHistoricoPedido;
        this.idUsuarioEmpleado = idUsuarioEmpleado;
        this.fecha = fecha;
    }

    public Integer getIdHistoricoPedido() {
        return idHistoricoPedido;
    }

    public void setIdHistoricoPedido(Integer idHistoricoPedido) {
        this.idHistoricoPedido = idHistoricoPedido;
    }

    public int getIdUsuarioEmpleado() {
        return idUsuarioEmpleado;
    }

    public void setIdUsuarioEmpleado(int idUsuarioEmpleado) {
        this.idUsuarioEmpleado = idUsuarioEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public EstadoPedido getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(EstadoPedido idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoPedido != null ? idHistoricoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoPedido)) {
            return false;
        }
        HistoricoPedido other = (HistoricoPedido) object;
        if ((this.idHistoricoPedido == null && other.idHistoricoPedido != null) || (this.idHistoricoPedido != null && !this.idHistoricoPedido.equals(other.idHistoricoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.HistoricoPedido[ idHistoricoPedido=" + idHistoricoPedido + " ]";
    }
    
}
