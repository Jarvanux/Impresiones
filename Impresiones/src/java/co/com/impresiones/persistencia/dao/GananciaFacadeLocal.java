/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Ganancia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface GananciaFacadeLocal {

    void crear(Ganancia ganancia);

    void editar(Ganancia ganancia);

    void remover(Ganancia ganancia);

    Ganancia filtrarPorID(Object id);

    List<Ganancia> listarTodo();

    List<Ganancia> listarPorRango(int[] range);

    int contarRegistros();
    
}
