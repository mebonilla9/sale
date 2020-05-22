/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.negocio.util;

import dev.manuel.estandar.dto.AuditoriaDTO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class AuditoriaUtil
{

  public static AuditoriaDTO auditoria() {
    /*Object usuarioPrincipal = SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
    if (usuarioPrincipal instanceof AuditoriaDTO) {
      return (AuditoriaDTO) usuarioPrincipal;
    }*/
    return new AuditoriaDTO();
  }
}
