/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.negocio.utilerias;

import java.util.Date;

/**
 *
 * @author jhonjaider1000
 */
public class DatosRespuesta {
    int entero;
    long enteroGrande;
    float datoFloat;
    double datoDouble;
    String string;
    Date fecha;
    //Métodos set y get.
    public int getEntero() {
        return entero;
    }

    public void setEntero(int entero) {
        this.entero = entero;
    }

    public long getEnteroGrande() {
        return enteroGrande;
    }

    public void setEnteroGrande(long enteroGrande) {
        this.enteroGrande = enteroGrande;
    }

    public float getDatoFloat() {
        return datoFloat;
    }

    public void setDatoFloat(float datoFloat) {
        this.datoFloat = datoFloat;
    }

    public double getDatoDouble() {
        return datoDouble;
    }

    public void setDatoDouble(double datoDouble) {
        this.datoDouble = datoDouble;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
