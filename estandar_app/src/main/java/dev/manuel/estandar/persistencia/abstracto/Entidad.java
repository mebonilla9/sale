/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.abstracto;


import dev.manuel.estandar.excepcion.AplicacionException;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public abstract class Entidad {

  private Object Info;

  public Object getInfo()
  {
    return Info;
  }

  public void setInfo(Object Info)
  {
    this.Info = Info;
  }

  public abstract <T extends Entidad> T validar()
          throws AplicacionException;

}
