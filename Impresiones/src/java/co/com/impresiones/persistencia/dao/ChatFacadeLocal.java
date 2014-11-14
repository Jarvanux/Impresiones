/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Chat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ChatFacadeLocal {

    void crear(Chat chat);
    
    void editar(Chat chat);

    void remover(Chat chat);

    Chat filtrarPorID(Object id);

    List<Chat> listarTodo();

    List<Chat> listarPorRango(int[] range);

    int contarRegistros();
    
}
