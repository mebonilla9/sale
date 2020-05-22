
package dev.manuel.sale.persistencia.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.manuel.estandar.excepcion.AplicacionException;
import dev.manuel.estandar.persistencia.abstracto.Entidad;
import dev.manuel.estandar.util.ValidarEntidad;

/**
 * @author Manuel Ernesto Bonilla Muñoz
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item extends Entidad implements Serializable {

    private Integer id;
    private String name;
    private String measureUnit;
    private String code;
    private Integer price;
    private Integer quantity;
    private String brand;

    // <editor-fold defaultstate="collapsed" desc="GET-SET">
    public Integer getId() {
        return id;
    }

    public Item setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public Item setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Item setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Item setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    // </editor-fold>

    @Override
    public Item validar() throws AplicacionException {
        ValidarEntidad.construir(this)
                .agregar("name",
                        "requerido",
                        "Debe asignar un nombre al articulo")
                .agregar("measureUnit",
                        "requerido",
                        "Debe asignar una unidad de medida al articulo")
                .agregar("code",
                        "requerido",
                        "Debe asignar un codigo interno al articulo")
                .agregar("price",
                        "requerido",
                        "Debe asignar un precio al articulo")
                .agregar("quantity",
                        "requerido",
                        "Debe asignar una cantidad de objetos del articulo")
                .agregar("brand",
                        "requerido",
                        "Debe asignar una marca al articulo")
                .validar();
        return this;
    }
}

