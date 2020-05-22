/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.constante;


import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public enum EMensajeEstandar implements IGenericoMensaje {
  OK(1, "Petición ejecutada correctamente"),
  NO_RESULTADOS(0, "No se encontraron resultados "),
  ERROR(-1, "Error al procesar la petición"),
  ERROR_CONECTAR(-5, "Error al conectar con el servidor"),
  ERROR_EDITAR(-2, "Error al editar"),
  ERROR_INSERTAR(-3, "Error al insertar el registro"),
  ERROR_CONSULTAR(-4, "Error al consultar el registro"),
  ERROR_COLUMNA_NO_ENCONTRADA(-5, "No se encontró la columna"),
  ERROR_ENTIDAD(-6, "La información a validar está vacía "),
  ERROR_CAMPO(-7, "El atributo __COMPLEMENTO__ no existe en la entidad "),
  ERROR_VALIDACION_MENSAJE(-8, "__COMPLEMENTO__"),
  ERROR_JSON_MAP(-9, "Error al procesar el json a un map"),
  ERROR_REGLAS(-10, "Debe especificar las validaciones que se van a realizar a la entidad "),
  ERROR_REGLA_NO_EXISTE(-11, "Error la regla de validación no existe"),
  ERROR_REGLAS_DEMASIADO(-12, "Error no se puede invocar este método porque hay más de un dato para validar "),
  ERROR_JSON(-13, "Error al convertir el objeto en un JSON");

  /**
   * Código del error
   */
  private final int codigo;
  /**
   * Mensaje del evento
   */
  private final String mensaje;

  private EMensajeEstandar(int codigo, String mensaje) {
    this.codigo = codigo;
    this.mensaje = mensaje;
  }

  @Override
  public int getCodigo() {
    return codigo;
  }

  @Override
  public String getMensaje() {
    return mensaje;
  }

}
