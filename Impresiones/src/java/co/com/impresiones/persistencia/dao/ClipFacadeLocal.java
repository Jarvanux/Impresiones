/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Clip;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ClipFacadeLocal {

    void crear(Clip clip);

    void editar(Clip clip);

    void remover(Clip clip);

    Clip filtrarPorID(Object id);

    List<Clip> listarTodo();

    List<Clip> listarPorRango(int[] range);

    int contarRegistros();
    
}
