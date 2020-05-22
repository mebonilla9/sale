package dev.manuel.sale.negocio.delegados;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.util.FuncionesDatoUtil;

import javax.sql.DataSource;

public abstract class GenericoDelegado extends FuncionesDatoUtil
{

    protected DataSource datasource;
    protected AuditoriaDTO auditoria;

    /**
     * Constructor de la clase
     *
     * @param datasource conexión a la base de datos
     * @param auditoria Información del usuario logeado
     */
    public GenericoDelegado(DataSource datasource, AuditoriaDTO auditoria) {
        this.datasource = datasource;
        this.auditoria = auditoria;
    }

}
