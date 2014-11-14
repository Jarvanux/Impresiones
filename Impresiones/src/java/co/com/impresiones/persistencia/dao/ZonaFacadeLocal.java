/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Zona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ZonaFacadeLocal {

    void crear(Zona zona);

    void editar(Zona zona);

    void remover(Zona zona);

    Zona filtrarPorID(Object id);

    List<Zona> listarTodo();

    List<Zona> listarPorRango(int[] range);

    int contarRegistros();
    
}
