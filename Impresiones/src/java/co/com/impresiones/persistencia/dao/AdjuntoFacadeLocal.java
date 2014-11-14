/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.dao;

import co.com.impresiones.persistencia.entidades.Adjunto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface AdjuntoFacadeLocal {

    void crear(Adjunto adjunto);

    void editar(Adjunto adjunto);

    void remover(Adjunto adjunto);

    Adjunto filtrarPorID(Object id);

    List<Adjunto> listarTodo();

    List<Adjunto> listarPorRango(int[] range);

    int contarRegistros();
    
}
