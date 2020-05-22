/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.exception;

import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class PersistenciaException extends AplicacionException {

  public PersistenciaException(IGenericoMensaje mensaje) {
    super(mensaje);
  }

  public PersistenciaException(IGenericoMensaje mensaje, Object datos) {
    super(mensaje, datos);
  }

  public PersistenciaException(IGenericoMensaje eMensaje, String complemento) {
    super(eMensaje);
    mensaje = eMensaje.getMensaje().replaceAll("__COMPLEMENTO__", complemento);
  }

}
