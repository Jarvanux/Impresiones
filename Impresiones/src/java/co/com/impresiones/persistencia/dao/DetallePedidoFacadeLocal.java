/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.DetallePedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface DetallePedidoFacadeLocal {

    void crear(DetallePedido detallePedido);

    void editar(DetallePedido detallePedido);

    void remover(DetallePedido detallePedido);

    DetallePedido filtrarPorID(Object id);

    List<DetallePedido> listarTodo();

    List<DetallePedido> listarPorRango(int[] range);

    int contarRegistros();
    
}
