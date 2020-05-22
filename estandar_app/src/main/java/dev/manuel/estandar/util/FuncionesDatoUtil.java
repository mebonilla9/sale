/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.excepcion.AplicacionException;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.LongValidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que tiene todas las funciones básicas que se pueden hacer un un dato
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class FuncionesDatoUtil {

  /**
   * Elimina los espacios al inicio y al final de una cadena
   *
   * @param valor cadena sin espacios o null si el dato no llega
   * @return
   */
  public static String trim(String valor)
  {
    return (valor == null) ? null : valor.trim();
  }

  /**
   * Quita los espacios del inicio y al final de la cadena y lo convierte en un
   * double
   *
   * @param valor Devuelve el valor, pero si llega un nulo devuelve un nulo
   * @return Valor convertido
   */
  public static Double parseDouble(String valor)
  {
    if (trim(valor) == null) {
      return null;
    }
    return Double.parseDouble(valor);
  }

  /**
   * Genera un error por caga de archivo con la estructura básica
   *
   * @param indice
   * @param mensaje
   * @return
   */
  public static Properties errorLinea(int indice, String mensaje)
  {
    Properties error = new Properties();
    error.put("linea", String.valueOf(indice));
    error.put("mensaje", mensaje);
    return error;
  }

  /**
   * Convierte una cada de texto con formato JSON devuelto por la base de datos
   * a una cadena de texto con formato JSON a clases JAVA
   *
   * @param json
   * @return
   */
  public static String deSeparadoPorGuionesACamelCase(String json)
  {
    StringBuffer sb = new StringBuffer();
    Matcher m = Pattern.compile("(_[a-z]{1})([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(json);
    while (m.find()) {
      String x = (m.group(1).toUpperCase() + m.group(2).toLowerCase());
      m.appendReplacement(sb, x);
    }
    json = m.appendTail(sb).toString().replaceAll("_", "");
    return json;
  }

//    public static String deCamelCaseASeparadoPorGuiones(String texto) {
//        String regex = "([a-z])([A-Z]+)";
//        String replacement = "$1_$2";
//        return texto
//                .replaceAll(regex, replacement)
//                .toLowerCase();
//    }
  /**
   * Método encargado de convertir un json en un Map se debe de asegurar que el
   * json sea un objeto y no una lista
   *
   * @param json Objeto que se quiere convertir en un mapa de llave - valor
   * @return
   * @throws AplicacionException
   */
  public static Map<String, String> jsonMap(String json)
          throws AplicacionException
  {
    if (json == null || "".equalsIgnoreCase(json.trim())) {
      return new HashMap<>();
    }
    TypeReference<Map<String, String>> tipo = new TypeReference<Map<String, String>>() {
    };
    try {
      return new ObjectMapper().readValue(json, tipo);
    } catch (IOException ex) {
      LogUtil.error(ex);
      throw new AplicacionException(EMensajeEstandar.ERROR_JSON_MAP);
    }
  }

  /**
   * Convierte un objeto en un string en formato JSON
   *
   * @param data Objeto que se quiere convertir en un JSON
   * @return String con la información convertidad
   * @throws AplicacionException Error al convertir
   */
  public static String json(Object data)
          throws AplicacionException
  {
    try {
      return new ObjectMapper().writeValueAsString(data);
    } catch (IOException ex) {
      LogUtil.error(ex);
      throw new AplicacionException(EMensajeEstandar.ERROR_JSON);
    }
  }

  /**
   * Validar que el campo tenga un valor
   *
   * @param <T>
   * @param valor campo o atributo a validar
   * @param mensaje campo de la interfaz
   * @return
   * @throws AplicacionException
   */
  @SuppressWarnings("UseOfObsoleteCollectionType")
  public static <T extends Object> T validarRequerido(Object valor, String mensaje)
          throws AplicacionException
  {
    if (valor == null) {
      throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, mensaje);
    }
    Class claseValor = valor.getClass();
    if (claseValor == List.class
            || ArrayList.class == claseValor) {
      ValidacionObjeto.listaObligatoria((List) valor, mensaje);
    }
    String valorTexto = valor.toString().trim();
    if (valorTexto.equalsIgnoreCase("")) {
      throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, mensaje);
    }
    return (T) valor;
  }

  /**
   * Validar la fecha de un atributo o campo
   *
   * @param valor Información o dato a validar
   * @param mensaje campo de la interfaz a validar
   * @return
   * @throws AplicacionException
   */
  public static Date validarFecha(Object valor, String mensaje)
          throws AplicacionException
  {
    if (valor == null) {
      return null;
    }

    if (valor instanceof Date) {
      return (Date) valor;
    }

    String valorTexto = valor.toString().trim();
    if (valorTexto.equalsIgnoreCase("")) {
      return null;
    }

    DateValidator validator = DateValidator.getInstance();
    Date fechaCorta = validator.validate(valorTexto, "yyyy-MM-dd");
    if (fechaCorta != null) {
      return fechaCorta;
    }
    Date fechaLarga = validator.validate(valorTexto, "yyyy-MM-dd hh:mm:ss");
    if (fechaLarga != null) {
      return fechaLarga;
    }
    throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, mensaje);
  }

  /**
   * Verifica si el valor es un correo válido
   *
   * @param valor texto a validar si es un correo
   * @param mensaje nombre del campo de la interfaz
   * @return
   * @throws AplicacionException
   */
  public static String validarCorreo(Object valor, String mensaje)
          throws AplicacionException
  {
    if (valor == null) {
      return null;
    }
    String valorTexto = valor.toString().trim();
    if (valorTexto.equalsIgnoreCase("")) {
      return null;
    }
    boolean esCorreo = EmailValidator.getInstance().isValid(valorTexto);
    if (esCorreo) {
      return valorTexto;
    }
    throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, mensaje);
  }

  /**
   * Valida que el valor sea un número, double o long
   *
   * @param <T>
   * @param valor
   * @param mensaje
   * @return
   * @throws AplicacionException
   */
  public static <T extends Number> T validarNumero(Object valor, String mensaje)
          throws AplicacionException
  {
    if (valor == null) {
      return null;
    }
    String valorTexto = valor.toString().trim();
    if (valorTexto.equalsIgnoreCase("")) {
      return null;
    }
    LongValidator validacionEnteroLargo = LongValidator.getInstance();
    Long numeroEnteroLargo = validacionEnteroLargo.validate(valorTexto);
    if (numeroEnteroLargo != null) {
      return (T) numeroEnteroLargo;
    }
    DoubleValidator validacion = DoubleValidator.getInstance();
    Double numero = validacion.validate(valorTexto);
    if (numero != null) {
      return (T) numero;
    }
    throw new AplicacionException(EMensajeEstandar.ERROR_VALIDACION_MENSAJE, mensaje);
  }

  /**
   * Verfica si el valor del valor es nulo devuelvo el valor por defecto
   *
   * @param valor Valor a evaluar
   * @param defecto Valor que se devuelve si se encuentra nulo
   * @return valor por defecto o el mismo valor
   */
  public static String valorPorDefecto(String valor, String defecto)
  {
    return (valor == null) ? defecto : valor;
  }

  /**
   * Verfica si el valor de la variable es nulo si es así devuelve un string
   * vacío
   *
   * @param valor Valor a evaluar
   * @return Valor o un string vacío
   */
  public static String valorPorDefecto(String valor)
  {
    return FuncionesDatoUtil.valorPorDefecto(valor, "");
  }

  /**
   * Verifica si una cadena de texto está vacía
   *
   * @param parametro Cadena a evaluar
   * @return TRUE está vacía
   */
  public static boolean vacio(String parametro)
  {
    return parametro == null || parametro.trim().isEmpty();
  }

  /**
   * Valida que el parámetro tenga un valor
   *
   * @param parametro
   * @return TRUE tiene valor FALSE está vacío
   */
  public static boolean tieneValor(String parametro)
  {
    return !vacio(parametro);
  }

  public static boolean nulo(Object data)
  {
    return data == null;
  }

  /**
   * Obtiene una lista vacía cuando el valor del parámetro esté vacío
   *
   * @param <T> Entidad u Objeto que se va a llenar la clase
   * @param lista Lista vacía o el parámetro con su valor a evaluar
   * @return
   */
  public static <T extends Object> List<T> valorPorDefecto(List<T> lista)
  {
    return lista == null ? new ArrayList<>() : lista;
  }

  /**
   * Verifica si el valor está en los parámetros dinámicos, si alguno de los
   * parámetros es nulo el método retorna FALSO y si el valor que está buscando
   * se encuentra el retorna un TRUE
   *
   * @param valor valor a buscar
   * @param valores posibles opciones de valores de búsqueda
   * @return FALSE si no lo encuentra o alguno de los dos párametros es nulo, de
   * lo contrario devuelve TRUE
   */
  public static boolean verificarValor(String valor, String... valores)
  {
    if (valores == null || valor == null) {
      return false;
    }
    for (String parametro : valores) {
      if (Objects.equals(valor, parametro)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Convierte una cadena con formato JSON y lo devuelve en la clase que se
   * desea realizar la conversión
   *
   * @param <T> Clase a la que se quiere convertir
   * @param json Cadena de caracteres con el formato json
   * @param referencia {@code  new TypeReference<List<Clase>>() }
   * @return Objeto con la información del json
   * @throws AplicacionException Error al realizar la conversión
   */
  public static <T extends Object> T jsonObjeto(String json, TypeReference<T> referencia)
          throws AplicacionException
  {
    if (tieneValor(json) && referencia != null) {
      try {
        ObjectMapper objeto = new ObjectMapper();
        return objeto.readValue(json, referencia);
      } catch (IOException ex) {
        LogUtil.error(ex);
      }
    }
    throw new AplicacionException(EMensajeEstandar.ERROR_JSON);
  }

  /**
   * Convierte una cadena con formato JSON y lo devuelve en la clase que se
   * desea realizar la conversión
   *
   * @param <T> Clase a la que se quiere convertir
   * @param json Cadena de caracteres con el formato json
   * @param referencia {@code  new TypeReference<List<Clase>>() }
   * @param defecto Valor que se quiere devolver en dado caso que no se puede
   * convertir el json
   * @return Objeto con la información del json
   */
  public static <T extends Object> T jsonObjetoPorDefecto(String json, TypeReference<T> referencia, Object defecto)
  {
    try {
      return jsonObjeto(json, referencia);
    } catch (AplicacionException ex) {
      LogUtil.error(ex);
      return (T) defecto;
    }
  }

  /**
   * Método encargado de obtener un recurso
   *
   * @param recurso Nombre del recurso
   * @return
   */
  public static InputStream getRecurso(String recurso)
  {
    return FuncionesDatoUtil.class.getResourceAsStream(recurso);
  }
  
  
  

}
