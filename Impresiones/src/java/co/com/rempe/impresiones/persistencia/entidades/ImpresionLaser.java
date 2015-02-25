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
@Table(name = "impresion_laser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpresionLaser.findAll", query = "SELECT i FROM ImpresionLaser i"),
    @NamedQuery(name = "ImpresionLaser.findByIdImpresion", query = "SELECT i FROM ImpresionLaser i WHERE i.idImpresion = :idImpresion"),
    @NamedQuery(name = "ImpresionLaser.findByTipoColor", query = "SELECT i FROM ImpresionLaser i WHERE i.tipoColor = :tipoColor"),
    @NamedQuery(name = "ImpresionLaser.findByHojasBn", query = "SELECT i FROM ImpresionLaser i WHERE i.hojasBn = :hojasBn"),
    @NamedQuery(name = "ImpresionLaser.findByHojasColor", query = "SELECT i FROM ImpresionLaser i WHERE i.hojasColor = :hojasColor"),
    @NamedQuery(name = "ImpresionLaser.findByCopias", query = "SELECT i FROM ImpresionLaser i WHERE i.copias = :copias"),
    @NamedQuery(name = "ImpresionLaser.findByModoImpresion", query = "SELECT i FROM ImpresionLaser i WHERE i.modoImpresion = :modoImpresion"),
    @NamedQuery(name = "ImpresionLaser.findByTamanoPapel", query = "SELECT i FROM ImpresionLaser i WHERE i.tamanoPapel = :tamanoPapel"),
    @NamedQuery(name = "ImpresionLaser.findByTipoPapel", query = "SELECT i FROM ImpresionLaser i WHERE i.tipoPapel = :tipoPapel"),
    @NamedQuery(name = "ImpresionLaser.findByTipoCarga", query = "SELECT i FROM ImpresionLaser i WHERE i.tipoCarga = :tipoCarga"),
    @NamedQuery(name = "ImpresionLaser.findByRutaArchivo", query = "SELECT i FROM ImpresionLaser i WHERE i.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "ImpresionLaser.findByLinkArchivo", query = "SELECT i FROM ImpresionLaser i WHERE i.linkArchivo = :linkArchivo"),
    @NamedQuery(name = "ImpresionLaser.findByValorImpresion", query = "SELECT i FROM ImpresionLaser i WHERE i.valorImpresion = :valorImpresion"),
    @NamedQuery(name = "ImpresionLaser.findByAnillado", query = "SELECT i FROM ImpresionLaser i WHERE i.anillado = :anillado"),
    @NamedQuery(name = "ImpresionLaser.findByColorAnillo", query = "SELECT i FROM ImpresionLaser i WHERE i.colorAnillo = :colorAnillo"),
    @NamedQuery(name = "ImpresionLaser.findByColorTapa1", query = "SELECT i FROM ImpresionLaser i WHERE i.colorTapa1 = :colorTapa1"),
    @NamedQuery(name = "ImpresionLaser.findByColorTapa2", query = "SELECT i FROM ImpresionLaser i WHERE i.colorTapa2 = :colorTapa2"),
    @NamedQuery(name = "ImpresionLaser.findByPlastificado", query = "SELECT i FROM ImpresionLaser i WHERE i.plastificado = :plastificado"),
    @NamedQuery(name = "ImpresionLaser.findByServicioCorte", query = "SELECT i FROM ImpresionLaser i WHERE i.servicioCorte = :servicioCorte"),
    @NamedQuery(name = "ImpresionLaser.findByCosidoTipoRevista", query = "SELECT i FROM ImpresionLaser i WHERE i.cosidoTipoRevista = :cosidoTipoRevista"),
    @NamedQuery(name = "ImpresionLaser.findByInstruccionesEspeciales", query = "SELECT i FROM ImpresionLaser i WHERE i.instruccionesEspeciales = :instruccionesEspeciales"),
    @NamedQuery(name = "ImpresionLaser.findByValorTotal", query = "SELECT i FROM ImpresionLaser i WHERE i.valorTotal = :valorTotal")})
public class ImpresionLaser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_impresion")
    private Integer idImpresion;
    @Column(name = "tipo_color")
    private Integer tipoColor;
    @Column(name = "hojas_bn")
    private Integer hojasBn;
    @Column(name = "hojas_color")
    private Integer hojasColor;
    @Column(name = "copias")
    private Integer copias;
    @Column(name = "modo_impresion")
    private Integer modoImpresion;
    @Column(name = "tamano_papel")
    private Integer tamanoPapel;
    @Column(name = "tipo_papel")
    private Integer tipoPapel;
    @Column(name = "tipo_carga")
    private Integer tipoCarga;
    @Size(max = 140)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    @Size(max = 300)
    @Column(name = "link_archivo")
    private String linkArchivo;
    @Column(name = "valor_impresion")
    private Double valorImpresion;
    @Column(name = "otros_costos")
    private Double otrosCostos;
    @Column(name = "anillado")
    private Boolean anillado;
    @Column(name = "color_anillo")
    private Integer colorAnillo;
    @Column(name = "color_tapa1")
    private Integer colorTapa1;
    @Column(name = "color_tapa2")
    private Integer colorTapa2;
    @Column(name = "plastificado")
    private Integer plastificado;
    @Column(name = "servicio_corte")
    private Integer servicioCorte;
    @Column(name = "cosido_tipo_revista")
    private Integer cosidoTipoRevista;
    @Size(max = 1000)
    @Column(name = "instrucciones_especiales")
    private String instruccionesEspeciales;
    @Size(max = 50)
    @Column(name = "valor_total")
    private String valorTotal;
    @Column(name = "guia_impresion")
    private String guiaImpresion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public ImpresionLaser() {
    }

    public ImpresionLaser(Integer idImpresion) {
        this.idImpresion = idImpresion;
    }

    public Integer getIdImpresion() {
        return idImpresion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setIdImpresion(Integer idImpresion) {
        this.idImpresion = idImpresion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGuiaImpresion() {
        return guiaImpresion;
    }

    public void setGuiaImpresion(String guiaImpresion) {
        this.guiaImpresion = guiaImpresion;
    }

    public Integer getTipoColor() {
        return tipoColor;
    }

    public void setTipoColor(Integer tipoColor) {
        this.tipoColor = tipoColor;
    }

    public Integer getHojasBn() {
        return hojasBn;
    }

    public void setHojasBn(Integer hojasBn) {
        this.hojasBn = hojasBn;
    }

    public Integer getHojasColor() {
        return hojasColor;
    }
    

    public void setHojasColor(Integer hojasColor) {
        this.hojasColor = hojasColor;
    }

    public Integer getCopias() {
        return copias;
    }

    public Double getOtrosCostos() {
        return otrosCostos;
    }

    public void setOtrosCostos(Double otrosCostos) {
        this.otrosCostos = otrosCostos;
    }

    
    public void setCopias(Integer copias) {
        this.copias = copias;
    }

    public Integer getModoImpresion() {
        return modoImpresion;
    }

    public void setModoImpresion(Integer modoImpresion) {
        this.modoImpresion = modoImpresion;
    }

    public Integer getTamanoPapel() {
        return tamanoPapel;
    }

    public void setTamanoPapel(Integer tamanoPapel) {
        this.tamanoPapel = tamanoPapel;
    }

    public Integer getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(Integer tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    public Integer getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(Integer tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getLinkArchivo() {
        return linkArchivo;
    }

    public void setLinkArchivo(String linkArchivo) {
        this.linkArchivo = linkArchivo;
    }

    public Double getValorImpresion() {
        return valorImpresion;
    }

    public void setValorImpresion(Double valorImpresion) {
        this.valorImpresion = valorImpresion;
    }

    public Boolean getAnillado() {
        return anillado;
    }

    public void setAnillado(Boolean anillado) {
        this.anillado = anillado;
    }

    public Integer getColorAnillo() {
        return colorAnillo;
    }

    public void setColorAnillo(Integer colorAnillo) {
        this.colorAnillo = colorAnillo;
    }

    public Integer getColorTapa1() {
        return colorTapa1;
    }

    public void setColorTapa1(Integer colorTapa1) {
        this.colorTapa1 = colorTapa1;
    }

    public Integer getColorTapa2() {
        return colorTapa2;
    }

    public void setColorTapa2(Integer colorTapa2) {
        this.colorTapa2 = colorTapa2;
    }

    public Integer getPlastificado() {
        return plastificado;
    }

    public void setPlastificado(Integer plastificado) {
        this.plastificado = plastificado;
    }

    public Integer getServicioCorte() {
        return servicioCorte;
    }

    public void setServicioCorte(Integer servicioCorte) {
        this.servicioCorte = servicioCorte;
    }

    public Integer getCosidoTipoRevista() {
        return cosidoTipoRevista;
    }

    public void setCosidoTipoRevista(Integer cosidoTipoRevista) {
        this.cosidoTipoRevista = cosidoTipoRevista;
    }

    public String getInstruccionesEspeciales() {
        return instruccionesEspeciales;
    }

    public void setInstruccionesEspeciales(String instruccionesEspeciales) {
        this.instruccionesEspeciales = instruccionesEspeciales;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpresion != null ? idImpresion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpresionLaser)) {
            return false;
        }
        ImpresionLaser other = (ImpresionLaser) object;
        if ((this.idImpresion == null && other.idImpresion != null) || (this.idImpresion != null && !this.idImpresion.equals(other.idImpresion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser[ idImpresion=" + idImpresion + " ]";
    }

}
