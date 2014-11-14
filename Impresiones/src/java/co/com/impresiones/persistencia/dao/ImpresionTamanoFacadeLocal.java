/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.ImpresionTamano;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ImpresionTamanoFacadeLocal {

    void crear(ImpresionTamano impresionTamano);

    void editar(ImpresionTamano impresionTamano);

    void remover(ImpresionTamano impresionTamano);

    ImpresionTamano filtrarPorID(Object id);

    List<ImpresionTamano> listarTodo();

    List<ImpresionTamano> listarPorRango(int[] range);

    int contarRegistros();
    
}
