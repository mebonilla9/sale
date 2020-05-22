package dev.manuel.estandar.persistencia.abstracto;

import dev.manuel.estandar.constante.EMensajeEstandar;
import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;
import dev.manuel.estandar.persistencia.util.PreparedStatementNamed;
import dev.manuel.estandar.persistencia.util.TablaUtil;
import dev.manuel.estandar.util.FuncionesDatoUtil;
import dev.manuel.estandar.util.LogUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
@SuppressWarnings("UseSpecificCatch")
public abstract class GenericoCRUD extends FuncionesDatoUtil {

  protected Connection cnn;
  protected Map<String, Object> parametros = new HashMap<>();
  protected AuditoriaDTO auditoria;
  private static final int NO_REGISTROS = 0;

  public GenericoCRUD(Connection cnn, AuditoriaDTO auditoria)
  {
    this.cnn = cnn;
    this.auditoria = auditoria;

  }

  protected static <T> T getObject(Integer pos, Class<T> tipo, ResultSet rs)
          throws PersistenciaException
  {
    try {
      String objDato = rs.getString(pos);
      if (objDato == null) {
        return null;
      }
      if (tipo == String.class) {
        return tipo.cast(objDato);
      }
      if (tipo == Boolean.class) {
        return tipo.cast(rs.getBoolean(pos));
      }
      if (tipo == Long.class) {
        return tipo.cast(rs.getLong(pos));
      }
      if (tipo == Integer.class) {
        return tipo.cast(rs.getInt(pos));
      }
      if (tipo == Timestamp.class || tipo == Date.class) {
        return tipo.cast(rs.getTimestamp(pos));
      }
      Object objValor = tipo.getMethod("valueOf", String.class).invoke(null, objDato);
      return tipo.cast(objValor);
    } catch (Exception ex) {
      LogUtil.error(ex);
      throw new PersistenciaException(EMensajeEstandar.ERROR_COLUMNA_NO_ENCONTRADA);
    }
  }

  public static <T> T getObject(String columna, Class<T> tipo, ResultSet rs)
          throws PersistenciaException
  {
    try {
      String objDato = rs.getString(columna);
      if (objDato == null) {
        return null;
      }
      if (tipo == String.class) {
        return tipo.cast(objDato);
      }
      if (tipo == Boolean.class) {
        return tipo.cast(rs.getBoolean(columna));
      }
      if (tipo == Long.class) {
        return tipo.cast(rs.getLong(columna));
      }
      if (tipo == Integer.class) {
        return tipo.cast(rs.getInt(columna));
      }
      if (tipo == Timestamp.class || tipo == Date.class) {
        return tipo.cast(rs.getTimestamp(columna));
      }
      Object objValor = tipo.getMethod("valueOf", String.class).invoke(null, objDato);
      return tipo.cast(objValor);
    } catch (Exception ex) {
      LogUtil.error(ex);
      throw new PersistenciaException(EMensajeEstandar.ERROR_COLUMNA_NO_ENCONTRADA);
    }
  }

  protected int ejecutarEdicion(StringBuilder sql, Map<String, Object> parametros)
          throws PersistenciaException
  {
    return ejecutarEdicion(sql, parametros, new ModificarAdaptador() {
    });
  }

  protected int ejecutarEdicion(StringBuilder sql, Map<String, Object> parametros, ModificarAdaptador adaptador)
          throws PersistenciaException
  {
    log(sql);
    PreparedStatementNamed ps = null;
    try {
      ps = new PreparedStatementNamed(cnn, sql.toString());
      Set<String> keys = parametros.keySet();
      for (String key : keys) {
        ps.setObject(key, parametros.get(key));
      }
      int quantity = ps.executeUpdate();
      if (quantity == NO_REGISTROS) {
        adaptador.sinResultados();
      }
      return quantity;
    } catch (SQLException ex) {
      LogUtil.error(ex);
      adaptador.error(ex);
      return NO_REGISTROS;
    } finally {
      desconectar(ps);
      this.parametros = new HashMap<>();
    }
  }

  protected <T> T ejecutarConsultaSimple(StringBuilder sql, Map<String, Object> parameters, ConsultaAdaptador<T> query)
          throws PersistenciaException
  {
    log(sql);
    PreparedStatementNamed ps = null;
    ResultSet rs = null;
    try {
      ps = new PreparedStatementNamed(cnn, sql.toString());
      Set<String> keys = parameters.keySet();
      for (String key : keys) {
        ps.setObject(key, parameters.get(key));
      }
      rs = ps.executeQuery();
      Map<String, Integer> columns = TablaUtil.getColumnasAlias(rs);
      if (rs.next()) {
        return query.siguiente(rs, columns);
      }
      query.sinResultados();
      return null;
    } catch (SQLException ex) {
      LogUtil.error(ex);
      query.error(ex);
      return null;
    } finally {
      desconectar(ps, rs);
      this.parametros = new HashMap<>();
    }
  }

  protected <T extends Object> List<T> ejecutarConsulta(StringBuilder sql, Map<String, Object> parameters, ConsultaAdaptador<T> adaptador)
          throws PersistenciaException
  {
    log(sql);
    PreparedStatementNamed ps = null;
    ResultSet rs = null;
    List<T> list = new ArrayList<>();
    try {
      ps = new PreparedStatementNamed(cnn, sql.toString());
      Set<String> keys = parameters.keySet();
      for (String key : keys) {
        ps.setObject(key, parameters.get(key));
      }
      rs = ps.executeQuery();
      Map<String, Integer> columns = TablaUtil.getColumnasAlias(rs);
      while (rs.next()) {
        T register = adaptador.siguiente(rs, columns);
        list.add(register);
      }
      if (list.isEmpty()) {
        adaptador.sinResultados();
      }
      return list;
    } catch (SQLException ex) {
      LogUtil.error(ex);
      adaptador.error(ex);
      return null;
    } finally {
      desconectar(ps, rs);
      this.parametros = new HashMap<>();
    }
  }

  protected void desconectar(PreparedStatement ps, ResultSet rs)
  {
    GenericoConexion.desconectar(ps, rs);
  }

  protected void desconectar(PreparedStatementNamed ps, ResultSet rs)
  {
    if (ps == null) {
      return;
    }
    GenericoConexion.desconectar(ps.getStatement(), rs);
  }

  protected void desconectar(PreparedStatement ps)
  {
    GenericoConexion.desconectar(ps);
  }

  protected void desconectar(PreparedStatementNamed ps)
  {
    if (ps == null) {
      return;
    }
    GenericoConexion.desconectar(ps.getStatement());
  }

  private void log(StringBuilder sql)
  {
    LogUtil.info(
            new StringBuilder("SQL: ")
                    .append(sql)
                    .append(" Parámetros: ")
                    .append(parametros)
                    .toString());

  }
}
