/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.abstracto;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;

import java.sql.SQLException;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public abstract class ModificarAdaptador {

  public void sinResultados()
          throws PersistenciaException
  {
  }

  public void error(SQLException ex)
          throws PersistenciaException
  {
    throw new PersistenciaException(EMensajeEstandar.ERROR_EDITAR);
  }

}
