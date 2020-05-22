/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.negocio.excepciones;

import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * Clase que controla las excepciones en la capa de lógica
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class NegocioException extends AplicacionException
{

  public NegocioException(IGenericoMensaje mensaje)
  {
    super(mensaje);
  }

  public NegocioException(IGenericoMensaje mensaje, Object datos)
  {
    super(mensaje, datos);
  }

  public NegocioException(IGenericoMensaje eMensaje, String complemento) {
    super(eMensaje, complemento);
    mensaje = eMensaje.getMensaje().replaceAll("__COMPLEMENTO__", complemento);
  }

  public NegocioException(IGenericoMensaje eMensaje, String complemento, Object datos) {
    this(eMensaje, complemento);
    this.datos = datos;
  }

}
