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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "detalle_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePedido.findAll", query = "SELECT d FROM DetallePedido d"),
    @NamedQuery(name = "DetallePedido.findByIdDetallePedidoProducto", query = "SELECT d FROM DetallePedido d WHERE d.idDetallePedidoProducto = :idDetallePedidoProducto"),
    @NamedQuery(name = "DetallePedido.findByIdColor", query = "SELECT d FROM DetallePedido d WHERE d.idColor = :idColor"),
    @NamedQuery(name = "DetallePedido.findByCantidad", query = "SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetallePedido.findByPrecioUnitario", query = "SELECT d FROM DetallePedido d WHERE d.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "DetallePedido.findByDescuento", query = "SELECT d FROM DetallePedido d WHERE d.descuento = :descuento")})
public class DetallePedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_pedido_producto")
    private Integer idDetallePedidoProducto;
    @Column(name = "id_color")
    private Integer idColor;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto idProducto;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido idPedido;

    public DetallePedido() {
    }

    public DetallePedido(Integer idDetallePedidoProducto) {
        this.idDetallePedidoProducto = idDetallePedidoProducto;
    }

    public Integer getIdDetallePedidoProducto() {
        return idDetallePedidoProducto;
    }

    public void setIdDetallePedidoProducto(Integer idDetallePedidoProducto) {
        this.idDetallePedidoProducto = idDetallePedidoProducto;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallePedidoProducto != null ? idDetallePedidoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePedido)) {
            return false;
        }
        DetallePedido other = (DetallePedido) object;
        if ((this.idDetallePedidoProducto == null && other.idDetallePedidoProducto != null) || (this.idDetallePedidoProducto != null && !this.idDetallePedidoProducto.equals(other.idDetallePedidoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.DetallePedido[ idDetallePedidoProducto=" + idDetallePedidoProducto + " ]";
    }
    
}
