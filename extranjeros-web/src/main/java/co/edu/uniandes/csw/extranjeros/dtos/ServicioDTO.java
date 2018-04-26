/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;

/**
 * ServicioDTO Objeto de transferencia de datos de Servicios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *    "id": number,
 *    "tipo": string,
 *    "adicional": boolean
 *   }
 * </pre>
 * Por ejemplo un servicio se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *    "id": 1321548,
 *    "tipo": "Agua",
 *    "adicional": false
 *   }
 *
 * </pre>
 * @author s.rodriguezm
 */
public class ServicioDTO {
    //Atributos
    private Long id;
    private String tipo;
    private Boolean adicional;
    private Double precioMensual;
    private String imagen;
    
    /**
     * Constructor por defecto
     */
    public ServicioDTO(){}
    
    
    /**
     * Crea un objeto ServicioDTO a partir de un objeto ServicioEntity.
     *
     * @param entity Entidad ServicioEntity desde la cual se va a crear el nuevo
     * objeto.
     * 
     */
    public ServicioDTO(ServicioEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.tipo = entity.getTipo();
            this.adicional = entity.getAdicional();
            this.precioMensual=entity.getPrecioMensual();
            this.imagen=entity.getImagen();
        }
    }
    //Métodos
    /**
     * @return el ID del servicio.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id el nuevo ID del Servicio.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return el tipo de servicio.
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * @param tipo el nuevo tipo del servicio.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * @return si es adicional o no.
     */
    public Boolean getAdicional() {
        return adicional;
    }
    /**
     * @param adicional si el servicio es adicional o no.
     */
    public void setAdicional(Boolean adicional) {
        this.adicional = adicional;
    }
    /**
     * @return precio mensual del servicio.
     */
    public Double getPrecioMensual() {
        return precioMensual;
    }
    /**
     * @param precioMensual nuevo precio mensual del servicio.
     */
    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }
    /**
     * @return url de la imagen del servicio.
     */
    public String getImagen() {
        return imagen;
    }
    /**
     * @param imagen nueva url de la imagen del servicio.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public ServicioEntity toEntity()
    {
        ServicioEntity e=new ServicioEntity();
        e.setAdicional(adicional);
        e.setId(id);
        e.setTipo(tipo);
        e.setPrecioMensual(precioMensual);
        e.setImagen(imagen);
        return e;
    }
    
}
