/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.TipoTamano;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TipoTamanoFacadeLocal {

    void crear(TipoTamano tipoTamano);

    void editar(TipoTamano tipoTamano);

    void remover(TipoTamano tipoTamano);

    TipoTamano filtrarPorID(Object id);

    List<TipoTamano> listarTodo();

    List<TipoTamano> listarPorRango(int[] range);

    int contarRegistros();
    
}
