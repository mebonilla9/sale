/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.util;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.excepcion.AplicacionException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class ValidarDato {

  private final List<DatoUtil> listaDatos = new ArrayList<>();

  private ValidarDato()
  {
  }

  /**
   * Agrega un valor para que se pueda validarConvertir la información
   *
   * @param dato Tipo de dato que se quiere validarConvertir
   * @param validaciones Las condiciones que debe de aplicar el dato
   * @param mensaje Mensaje que se mostrará si hay algún error
   * @return
   */
  public ValidarDato agregar(Object dato, String validaciones, String mensaje)
  {
    listaDatos.add(new DatoUtil(dato, validaciones, mensaje));
    return this;
  }

  /**
   * Crea una instancia de validarConvertir dato
   *
   * @return Instancia de la clase
   */
  public static ValidarDato construir()
  {
    return new ValidarDato();
  }

  /**
   * Si se está validando más de un dato el método valida todos los campos pero
   * al final devuelve un null
   *
   * @throws AplicacionException
   */
  public void validar()
          throws AplicacionException
  {
    if (listaDatos.isEmpty()) {
      throw new AplicacionException(EMensajeEstandar.ERROR_REGLA_NO_EXISTE);
    }
    for (DatoUtil dato : listaDatos) {
      dato.validar(null);
    }

  }

  /**
   * Valida y convierte un dato a la clase especificada por parámetro, este
   * método solo aplica para tipos de datos numéricos y texto es decir: long,
   * int, float, double, string
   *
   * @param <T> Objeto convertido
   * @param clase Clase a convertir (Integer,Long,Double, Float)
   * @return retorna el valor numérico
   * @throws AplicacionException
   */
  public <T extends Object> T validarConvertir(Class clase)
          throws AplicacionException
  {
    if (listaDatos.isEmpty()) {
      throw new AplicacionException(EMensajeEstandar.ERROR_REGLA_NO_EXISTE);
    }
    if (listaDatos.size() > 1) {
      throw new AplicacionException(EMensajeEstandar.ERROR_REGLAS_DEMASIADO);
    }
    DatoUtil dato = listaDatos.get(0);
    return dato.validar(clase);
  }

}
