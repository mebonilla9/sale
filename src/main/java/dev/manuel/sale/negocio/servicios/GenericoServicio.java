package dev.manuel.sale.negocio.servicios;

import dev.manuel.estandar.dto.AuditoriaDTO;
import dev.manuel.estandar.util.FuncionesDatoUtil;
import dev.manuel.sale.negocio.util.AuditoriaUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class GenericoServicio extends FuncionesDatoUtil {

    protected static final int NO_REGISTROS = 0;

    @Autowired
    protected DataSource dataSource;

    public AuditoriaDTO auditoria()
    {
        return AuditoriaUtil.auditoria();
    }

}
