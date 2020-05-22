/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.negocio.delegados;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.sale.persistencia.dao.ItemDAO;

import javax.sql.DataSource;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class ItemDelegado extends GenericoDelegado {

    private final ItemDAO itemDAO;

    public ItemDelegado(DataSource dataSource, AuditoriaDTO auditoria) throws AplicacionException {
        super(dataSource, auditoria);
        itemDAO = new ItemDAO(dataSource, auditoria);
    }

}
