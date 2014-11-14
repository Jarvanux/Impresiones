/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.HistoricoPedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface HistoricoPedidoFacadeLocal {

    void crear(HistoricoPedido historicoPedido);

    void editar(HistoricoPedido historicoPedido);

    void remover(HistoricoPedido historicoPedido);

    HistoricoPedido filtrarPorID(Object id);

    List<HistoricoPedido> listarTodo();

    List<HistoricoPedido> listarPorRango(int[] range);

    int contarRegistros();
    
}
