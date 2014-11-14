/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Contacto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ContactoFacadeLocal {

    void crear(Contacto contacto);

    void editar(Contacto contacto);

    void remover(Contacto contacto);

    Contacto filtrarPorID(Object id);

    List<Contacto> listarTodo();

    List<Contacto> listarPorRango(int[] range);

    int contarRegistros();
    
}
