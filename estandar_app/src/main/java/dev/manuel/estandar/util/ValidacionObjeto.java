/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.util;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.excepcion.AplicacionException;

import java.util.List;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class ValidacionObjeto {

  /**
   * Valida si la colección está vacía si es así lanza un error diciendo que la
   * lista es obligatoria
   *
   * @param lista
   * @param campo
   * @throws AplicacionException
   */
  public static void listaObligatoria(List lista, String campo)
          throws AplicacionException
  {
    if (lista == null || lista.isEmpty()) {
      throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, campo);
    }
  }
}
