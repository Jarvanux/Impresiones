/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ProductoFacadeLocal {

    void crear(Producto producto);

    void editar(Producto producto);

    void remover(Producto producto);

    Producto filtrarPorID(Object id);

    List<Producto> listarTodo();

    List<Producto> listarPorRango(int[] range);

    int contarRegistros();
    
}
