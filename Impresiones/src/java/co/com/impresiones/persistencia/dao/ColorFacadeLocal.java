/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Color;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ColorFacadeLocal {

    void crear(Color color);

    void editar(Color color);

    void remover(Color color);

    Color filtrarPorID(Object id);

    List<Color> listarTodo();

    List<Color> listarPorRango(int[] range);

    int contarRegistros();
    
}
