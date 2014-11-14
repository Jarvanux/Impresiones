/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.EstadoPedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface EstadoPedidoFacadeLocal {

    void crear(EstadoPedido estadoPedido);

    void editar(EstadoPedido estadoPedido);

    void remover(EstadoPedido estadoPedido);

    EstadoPedido filtrarPorID(Object id);

    List<EstadoPedido> listarTodo();

    List<EstadoPedido> listarPorRango(int[] range);

    int contarRegistros();
    
}
