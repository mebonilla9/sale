package dev.manuel.sale.persistencia.dao;

import javax.sql.DataSource;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;
import dev.manuel.sale.persistencia.dao.crud.ItemCRUD;

public class ItemDAO extends ItemCRUD {

  public ItemDAO(DataSource datasource, AuditoriaDTO auditoria) throws PersistenciaException {
     super(datasource, auditoria);
  }
}
