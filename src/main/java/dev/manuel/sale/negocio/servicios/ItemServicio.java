package dev.manuel.sale.negocio.servicios;

import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.exception.PersistenciaException;
import dev.manuel.sale.persistencia.dao.ItemDAO;
import dev.manuel.sale.persistencia.entidades.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServicio extends GenericoServicio {

    @Transactional(readOnly = true)
    public List<Item> consultarItems() throws AplicacionException {
        return new ItemDAO(dataSource, auditoria()).consultar();
    }

    @Transactional(rollbackFor = Throwable.class)
    public void guardar(Item item) throws AplicacionException {
        item.validar();
        ItemDAO itemDAO = new ItemDAO(dataSource, auditoria());
        if (item.getId() != null){
            itemDAO.editar(item);
            return;
        }
        itemDAO.insertar(item);
    }

}
