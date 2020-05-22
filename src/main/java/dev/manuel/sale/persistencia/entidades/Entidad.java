
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.persistencia.entidades;

import java.util.Map;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class Entidad {

    
    private Map<String,Object> datosAdicionales;

    public Map<String, Object> getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(Map<String, Object> datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }
}
