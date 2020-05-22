/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.negocio.controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;
import dev.manuel.estandar.util.LogUtil;
import dev.manuel.sale.negocio.constantes.EMensajeNegocio;
import dev.manuel.sale.negocio.excepciones.NegocioException;
import dev.manuel.sale.negocio.util.AuditoriaUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.util.List;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class GenericoControlador
{

  @ExceptionHandler({
    AplicacionException.class,
    PersistenciaException.class,
    NegocioException.class
  })
  @ResponseStatus(HttpStatus.OK)
  public RespuestaDTO controlarError(AplicacionException e) {
    return new RespuestaDTO(e.getCodigo(), e.getMensaje())
            .setDatos(e.getDatos());
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({
    MultipartException.class
  })
  public RespuestaDTO controlarError(MultipartException ex) {
    return new RespuestaDTO(EMensajeNegocio.ERROR_PETICION_GRANDE);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(value = HttpStatus.OK)
  public RespuestaDTO handleException(MethodArgumentNotValidException ex) {
    List<ObjectError> listaErrores = ex.getBindingResult().getAllErrors();
    StringBuilder mensajes = new StringBuilder();

    for (ObjectError error : listaErrores) {
      mensajes.append(error.getDefaultMessage())
              .append(" ");
    }
    return new RespuestaDTO(EMensajeNegocio.ERROR).setMensaje(mensajes.toString());
  }

  @ExceptionHandler({
    JsonProcessingException.class,
    MismatchedInputException.class,
    JsonMappingException.class
  })
  @ResponseStatus(HttpStatus.OK)
  public RespuestaDTO controlarError(JsonProcessingException ex) {
    return new RespuestaDTO(EMensajeNegocio.ERROR_JSON);
  }

  @ExceptionHandler({
    Throwable.class
  })
  @ResponseStatus(HttpStatus.OK)
  public RespuestaDTO controlarError(Throwable ex) {
    LogUtil.error(ex);
    return new RespuestaDTO(EMensajeNegocio.ERROR_FATAL);
  }

  public AuditoriaDTO auditoria()
  {
    return AuditoriaUtil.auditoria();
  }

}
