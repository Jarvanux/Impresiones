/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "adjunto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adjunto.findAll", query = "SELECT a FROM Adjunto a"),
    @NamedQuery(name = "Adjunto.findByIdAdjunto", query = "SELECT a FROM Adjunto a WHERE a.idAdjunto = :idAdjunto"),
    @NamedQuery(name = "Adjunto.findByNombre", query = "SELECT a FROM Adjunto a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Adjunto.findByNombreArchivo", query = "SELECT a FROM Adjunto a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "Adjunto.findByHojasColor", query = "SELECT a FROM Adjunto a WHERE a.hojasColor = :hojasColor"),
    @NamedQuery(name = "Adjunto.findByHojasNegro", query = "SELECT a FROM Adjunto a WHERE a.hojasNegro = :hojasNegro"),
    @NamedQuery(name = "Adjunto.findByNumeroHojas", query = "SELECT a FROM Adjunto a WHERE a.numeroHojas = :numeroHojas"),
    @NamedQuery(name = "Adjunto.findByNumeroHojasColor", query = "SELECT a FROM Adjunto a WHERE a.numeroHojasColor = :numeroHojasColor"),
    @NamedQuery(name = "Adjunto.findByNumeroHojasNegro", query = "SELECT a FROM Adjunto a WHERE a.numeroHojasNegro = :numeroHojasNegro"),
    @NamedQuery(name = "Adjunto.findByIdTipoImpresion", query = "SELECT a FROM Adjunto a WHERE a.idTipoImpresion = :idTipoImpresion")})
public class Adjunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adjunto")
    private Integer idAdjunto;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Lob
    @Size(max = 65535)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 255)
    @Column(name = "hojas_color")
    private String hojasColor;
    @Size(max = 255)
    @Column(name = "hojas_negro")
    private String hojasNegro;
    @Column(name = "numero_hojas")
    private Integer numeroHojas;
    @Column(name = "numero_hojas_color")
    private Integer numeroHojasColor;
    @Column(name = "numero_hojas_negro")
    private Integer numeroHojasNegro;
    @Column(name = "id_tipo_impresion")
    private Integer idTipoImpresion;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido idPedido;

    public Adjunto() {
    }

    public Adjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getHojasColor() {
        return hojasColor;
    }

    public void setHojasColor(String hojasColor) {
        this.hojasColor = hojasColor;
    }

    public String getHojasNegro() {
        return hojasNegro;
    }

    public void setHojasNegro(String hojasNegro) {
        this.hojasNegro = hojasNegro;
    }

    public Integer getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(Integer numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public Integer getNumeroHojasColor() {
        return numeroHojasColor;
    }

    public void setNumeroHojasColor(Integer numeroHojasColor) {
        this.numeroHojasColor = numeroHojasColor;
    }

    public Integer getNumeroHojasNegro() {
        return numeroHojasNegro;
    }

    public void setNumeroHojasNegro(Integer numeroHojasNegro) {
        this.numeroHojasNegro = numeroHojasNegro;
    }

    public Integer getIdTipoImpresion() {
        return idTipoImpresion;
    }

    public void setIdTipoImpresion(Integer idTipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
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
        hash += (idAdjunto != null ? idAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adjunto)) {
            return false;
        }
        Adjunto other = (Adjunto) object;
        if ((this.idAdjunto == null && other.idAdjunto != null) || (this.idAdjunto != null && !this.idAdjunto.equals(other.idAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.Adjunto[ idAdjunto=" + idAdjunto + " ]";
    }
    
}
