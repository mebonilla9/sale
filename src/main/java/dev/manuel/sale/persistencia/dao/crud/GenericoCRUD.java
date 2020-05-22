
package dev.manuel.sale.persistencia.dao.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.util.LogUtil;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public abstract class GenericoCRUD {

    protected Connection cnn;
    protected AuditoriaDTO auditoria;


    protected static <T> T getObject(Integer pos, Class<T> tipo, ResultSet rs) throws SQLException {
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
            throw new SQLException("Error al convertir el tipo de dato");
        }
    }

    public static <T> T getObject(String columna, Class<T> tipo, ResultSet rs) throws SQLException {
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
            throw new SQLException("Error al convertir el tipo de dato");
        }
    }

}
