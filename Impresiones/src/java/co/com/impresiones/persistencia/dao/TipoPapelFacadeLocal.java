/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.TipoPapel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TipoPapelFacadeLocal {

    void crear(TipoPapel tipoPapel);

    void editar(TipoPapel tipoPapel);

    void remover(TipoPapel tipoPapel);

    TipoPapel filtrarPorID(Object id);

    List<TipoPapel> listarTodo();

    List<TipoPapel> listarPorRango(int[] range);

    int contarRegistros();
    
}
