/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.constantes;

/**
 *
 * @author hrey
 */
public enum ECodigoRespuesta {

    CORRECTO(1, "Terminó la petición con éxito"),
    ERROR(-1, "No se puede terminar la petición"),
    VACIO(0, "No hay resultados");
    
    private int codigo;
    private String descripcion;

    private ECodigoRespuesta(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
