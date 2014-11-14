/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface UsuarioFacadeLocal {

    void crear(Usuario usuario);

    void editar(Usuario usuario);

    void remover(Usuario usuario);

    Usuario filtrarPorID(Object id);

    List<Usuario> listarTodo();

    List<Usuario> listarPorRango(int[] range);

    int contarRegistros();
    
}
