/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.estandar.persistencia.abstracto;

import dev.manuel.estandar.util.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
public abstract class GenericoConexion {

    public static void desconectar(Connection cnn) {
        desconectar(cnn, null, null);
    }

    public static void desconectar(PreparedStatement ps) {
        desconectar(null, ps, null);
    }

    public static void desconectar(PreparedStatement ps, ResultSet rs) {
        desconectar(null, ps, rs);
    }

    public static void desconectar(Connection cnn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn == null) {
                return;
            }
            if (cnn.isClosed()) {
                return;
            }
            cnn.setAutoCommit(false);
            cnn.close();
        } catch (SQLException ex) {
            LogUtil.error(ex);
        }
    }

    public static void rollback(Connection cnn) {
        try {
            if (cnn == null) {
                return;
            }
            if (cnn.isClosed()) {
                return;
            }
            cnn.rollback();
        } catch (SQLException ex) {
            LogUtil.error(ex);
        }
    }

}
