/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Pedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface PedidoFacadeLocal {

    void crear(Pedido pedido);

    void editar(Pedido pedido);

    void remover(Pedido pedido);

    Pedido filtrarPorID(Object id);

    List<Pedido> listarTodo();

    List<Pedido> listarPorRango(int[] range);

    int contarRegistros();
    
}
