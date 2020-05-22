/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.negocio.constantes;

import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public enum EMensajeNegocio implements IGenericoMensaje
{
  NO_RESULTADOS(0, "No se encontraron resultados "),
  OK(1, "Petición ejecutada correctamente"),
  OK_MENSAJE(2, "Petición ejecutada correctamente"),
  ERROR(-1, "Error al procesar la petición"),
  ERROR_FATAL(-505, "Error inesperado, comuníquese con el administrador del sistema "),
  ERROR_JSON(-506, "Error al converitr al JSON"),
  ERROR_PETICION_GRANDE(-529, "Error el tamaño del archivo es demasiado grande");

  /**
   * Código del error
   */
  private final int codigo;
  /**
   * Mensaje del evento
   */
  private final String mensaje;

  private EMensajeNegocio(int codigo, String mensaje)
  {
    this.codigo = codigo;
    this.mensaje = mensaje;
  }

  @Override
  public int getCodigo()
  {
    return codigo;
  }

  @Override
  public String getMensaje()
  {
    return mensaje;
  }

}
