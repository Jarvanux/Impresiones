/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.TipoImpresion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TipoImpresionFacadeLocal {

    void crear(TipoImpresion tipoImpresion);

    void editar(TipoImpresion tipoImpresion);

    void remover(TipoImpresion tipoImpresion);

    TipoImpresion filtrarPorID(Object id);

    List<TipoImpresion> listarTodo();

    List<TipoImpresion> listarPorRango(int[] range);

    int contarRegistros();
    
}
