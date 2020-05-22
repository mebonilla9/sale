/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.manuel.sale.persistencia.dao.crud;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Manuel Ernesto Bonilla Muñoz
 */
public interface IGenericoDAO<T> {

    public abstract void insertar(T entidad) throws SQLException;

    public abstract void insertarTodos(T entidad) throws SQLException;

    public abstract void editar(T entidad) throws SQLException;

    public List<T> consultar() throws SQLException;

    public T consultar(Long id) throws SQLException;


}
