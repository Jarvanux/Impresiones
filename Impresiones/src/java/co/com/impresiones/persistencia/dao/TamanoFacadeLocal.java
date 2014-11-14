/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Tamano;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TamanoFacadeLocal {

    void crear(Tamano tamano);

    void editar(Tamano tamano);

    void remover(Tamano tamano);

    Tamano filtrarPorID(Object id);

    List<Tamano> listarTodo();

    List<Tamano> listarPorRango(int[] range);

    int contarRegistros();
    
}
