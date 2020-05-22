package dev.manuel.sale.negocio.controladores;

import dev.manuel.estandar.dto.RespuestaDTO;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.sale.negocio.constantes.ERuta;
import dev.manuel.sale.negocio.servicios.ItemServicio;
import dev.manuel.sale.persistencia.entidades.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemControlador extends GenericoControlador {

  @Autowired
  private ItemServicio itemServicio;

  @PostMapping(ERuta.Item.GUARDAR)
  public RespuestaDTO guardar(@RequestBody Item item) throws AplicacionException {
    itemServicio.guardar(item);
    return new RespuestaDTO();
  }

  @PostMapping(ERuta.Item.CONSULTAR)
  public RespuestaDTO consultar() throws AplicacionException {
    List<Item> listaItems = itemServicio.consultarItems();
    return new RespuestaDTO().setDatos(listaItems);
  }


}
