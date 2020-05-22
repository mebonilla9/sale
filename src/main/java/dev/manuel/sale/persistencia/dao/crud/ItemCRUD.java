package dev.manuel.sale.persistencia.dao.crud;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.persistencia.abstracto.GenericoCRUD;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;
import dev.manuel.estandar.util.LogUtil;
import dev.manuel.sale.persistencia.basedatos.PostgresBD;
import dev.manuel.sale.persistencia.constante.EMensajePersistencia;
import dev.manuel.sale.persistencia.entidades.Item;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemCRUD extends GenericoCRUD {

  private final int ID = 1;

  public ItemCRUD(DataSource dataSource, AuditoriaDTO auditoria) throws PersistenciaException {
    super(PostgresBD.getConexion(dataSource), auditoria);
  }


  public void insertar(Item item) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.item(name,measure_unit,code,price,quantity,brand) VALUES (?,?,?,?,?,?)";
      sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      sentencia.setObject(i++, item.getName());
      sentencia.setObject(i++, item.getMeasureUnit());
      sentencia.setObject(i++, item.getCode());
      sentencia.setObject(i++, item.getPrice());
      sentencia.setObject(i++, item.getQuantity());
      sentencia.setObject(i++, item.getBrand());

      sentencia.executeUpdate();
      ResultSet rs = sentencia.getGeneratedKeys();
      if (rs.next()) {
        item.setId(rs.getInt("id"));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void insertarTodos(Item item) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "INSERT INTO public.item(id,name,measure_unit,code,price,quantity,brand) VALUES (?,?,?,?,?,?,?)";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, item.getId());
      sentencia.setObject(i++, item.getName());
      sentencia.setObject(i++, item.getMeasureUnit());
      sentencia.setObject(i++, item.getCode());
      sentencia.setObject(i++, item.getPrice());
      sentencia.setObject(i++, item.getQuantity());
      sentencia.setObject(i++, item.getBrand());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_INSERTAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public void editar(Item item) throws PersistenciaException {
    PreparedStatement sentencia = null;
    try {
      int i = 1;
      String sql = "UPDATE public.item SET name=?,measure_unit=?,code=?,price=?,quantity=?,brand=? where id=? ";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setObject(i++, item.getName());
      sentencia.setObject(i++, item.getMeasureUnit());
      sentencia.setObject(i++, item.getCode());
      sentencia.setObject(i++, item.getPrice());
      sentencia.setObject(i++, item.getQuantity());
      sentencia.setObject(i++, item.getBrand());
      sentencia.setObject(i++, item.getId());

      sentencia.executeUpdate();
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_EDITAR);
    } finally {
      desconectar(sentencia);
    }
  }


  public List<Item> consultar() throws PersistenciaException {
    PreparedStatement sentencia = null;
    List<Item> lista = new ArrayList<>();
    try {

      String sql = "SELECT * FROM public.item";
      sentencia = cnn.prepareStatement(sql);
      ResultSet rs = sentencia.executeQuery();
      while (rs.next()) {
        lista.add(getItem(rs));
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return lista;

  }


  public Item consultar(long id) throws PersistenciaException {
    PreparedStatement sentencia = null;
    Item obj = null;
    try {

      String sql = "SELECT * FROM public.item WHERE id=?";
      sentencia = cnn.prepareStatement(sql);
      sentencia.setLong(1, id);
      ResultSet rs = sentencia.executeQuery();
      if (rs.next()) {
        obj = getItem(rs);
      }
    } catch (SQLException e) {
      LogUtil.error(e);
      throw new PersistenciaException(EMensajePersistencia.ERROR_CONSULTAR);
    } finally {
      desconectar(sentencia);
    }
    return obj;
  }

  public static Item getItem(ResultSet rs) throws PersistenciaException {
    Item item = new Item();
    item.setId(getObject("id", Integer.class, rs));
    item.setName(getObject("name", String.class, rs));
    item.setMeasureUnit(getObject("measure_unit", String.class, rs));
    item.setCode(getObject("code", String.class, rs));
    item.setPrice(getObject("price", Integer.class, rs));
    item.setQuantity(getObject("quantity", Integer.class, rs));
    item.setBrand(getObject("brand", String.class, rs));

    return item;
  }

  public static void getItem(ResultSet rs, Map<String, Integer> columnas, Item item) throws PersistenciaException {
    Integer columna = columnas.get("item_id");
    if (columna != null) {
      item.setId(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("item_name");
    if (columna != null) {
      item.setName(getObject(columna, String.class, rs));
    }
    columna = columnas.get("item_measure_unit");
    if (columna != null) {
      item.setMeasureUnit(getObject(columna, String.class, rs));
    }
    columna = columnas.get("item_code");
    if (columna != null) {
      item.setCode(getObject(columna, String.class, rs));
    }
    columna = columnas.get("item_price");
    if (columna != null) {
      item.setPrice(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("item_quantity");
    if (columna != null) {
      item.setQuantity(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get("item_brand");
    if (columna != null) {
      item.setBrand(getObject(columna, String.class, rs));
    }
  }

  public static void getItem(ResultSet rs, Map<String, Integer> columnas, Item item, String alias) throws PersistenciaException {
    Integer columna = columnas.get(alias + "_id");
    if (columna != null) {
      item.setId(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_name");
    if (columna != null) {
      item.setName(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_measure_unit");
    if (columna != null) {
      item.setMeasureUnit(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_code");
    if (columna != null) {
      item.setCode(getObject(columna, String.class, rs));
    }
    columna = columnas.get(alias + "_price");
    if (columna != null) {
      item.setPrice(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_quantity");
    if (columna != null) {
      item.setQuantity(getObject(columna, Integer.class, rs));
    }
    columna = columnas.get(alias + "_brand");
    if (columna != null) {
      item.setBrand(getObject(columna, String.class, rs));
    }
  }

  public static Item getItem(ResultSet rs, Map<String, Integer> columnas) throws PersistenciaException {
    Item item = new Item();
    getItem(rs, columnas, item);
    return item;
  }

  public static Item getItem(ResultSet rs, Map<String, Integer> columnas, String alias) throws PersistenciaException {
    Item item = new Item();
    getItem(rs, columnas, item, alias);
    return item;
  }

}
