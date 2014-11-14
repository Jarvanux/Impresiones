/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface RolFacadeLocal {

    void crear(Rol rol);

    void editar(Rol rol);

    void remover(Rol rol);

    Rol filtrarPorID(Object id);

    List<Rol> listarTodo();

    List<Rol> listarPorRango(int[] range);

    int contarRegistros();
    
}
