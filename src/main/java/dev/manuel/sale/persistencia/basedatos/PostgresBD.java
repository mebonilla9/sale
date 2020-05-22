/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.persistencia.basedatos;

import dev.manuel.estandar.persistencia.exception.PersistenciaException;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import dev.manuel.estandar.persistencia.abstracto.GenericoConexion;
import dev.manuel.estandar.util.LogUtil;
import dev.manuel.sale.persistencia.constante.EMensajePersistencia;
import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public class PostgresBD extends GenericoConexion {

  public static Connection getConexion(DataSource datasource) throws PersistenciaException {
    try {
      return DataSourceUtils.doGetConnection(datasource);
    } catch (SQLException ex) {
      LogUtil.error(ex);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONEXION_BD);
    }
  }

  public static void commit(DataSource datasource) throws PersistenciaException {
    commit(getConexion(datasource));
  }

  public static void commit(Connection cnn) throws PersistenciaException {
    try {
      if (cnn == null) {
        return;
      }
      if (cnn.isClosed()) {
        return;
      }
      cnn.commit();
    } catch (SQLException ex) {
      LogUtil.error(ex);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONFIRMAR);
    }
  }

  public static void rollback(DataSource datasource) throws PersistenciaException {
    try {
      getConexion(datasource).rollback();
    } catch (SQLException ex) {
      LogUtil.error(ex);
    }
  }

}
