/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.excepcion;

import dev.manuel.estandar.plantilla.IGenericoMensaje;

/**
 * Clase de error que se va a devolver a la presentación
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class AplicacionException extends Exception {

  /**
   * Código de respuesta que se envía a la presentación del sistema
   */
  protected int codigo;

  /**
   * Descripción del error
   */
  protected String mensaje;

  /**
   * Información adicional del error
   */
  protected Object datos;

  /**
   * Constructor de la clase
   *
   * @param mensaje Constante del error que está orriendo
   */
  public AplicacionException(IGenericoMensaje mensaje) {
    this.codigo = mensaje.getCodigo();
    this.mensaje = mensaje.getMensaje();
  }

  public AplicacionException(IGenericoMensaje mensaje, Object datos) {
    this.codigo = mensaje.getCodigo();
    this.mensaje = mensaje.getMensaje();
    this.datos = datos;
  }

  public AplicacionException(int codigo, String mensaje) {
    this.codigo = codigo;
    this.mensaje = mensaje;
  }

  public AplicacionException(IGenericoMensaje eMensaje, String complemento) {
    this(eMensaje);
    mensaje = eMensaje.getMensaje().replaceAll("__COMPLEMENTO__", complemento);
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Object getDatos() {
    return datos;
  }

  public void setDatos(Object datos) {
    this.datos = datos;
  }

}
