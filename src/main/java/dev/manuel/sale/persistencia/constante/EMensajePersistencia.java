/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.persistencia.constante;

import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public enum EMensajePersistencia implements IGenericoMensaje
{

  OK(1, "Se ejecutó correctamente"),
  EDICION_OK(1, "Se hizo la edición"),
  NO_RESULTADOS(0, "No se encontraron registros"),
  NO_RESULTADOS_PERSONALIZADO(0, "No se encontraron __COMPLEMENTO__"),
  ERROR_CONEXION_BD(-1, "No hay conexión con la base de datos"),
  ERROR_CONSULTAR(-2, "Error al consultar el registro"),
  ERROR_MODIFICAR(-3, "Error al modificar el registro"),
  ERROR_COLUMNA_NO_ENCONTRADA(-4, "No se encontró la columna"),
  ERROR_INSERTAR(-5, "Error al insertar el registro"),
  ERROR_EDITAR(-7, "Error al editar"),
  ERROR_CONFIRMAR(-16, "Error al confirmar los cambios en la base de datos");

  /**
   * Código de respuesta
   */
  private final int codigo;

  /**
   * Descripción del mensaje o el evento que sucedió
   */
  private final String mensaje;

  private EMensajePersistencia(int codigo, String mensaje)
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
