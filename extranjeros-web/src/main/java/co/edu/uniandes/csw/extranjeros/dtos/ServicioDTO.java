/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 *
 * @author s.rodriguezm
 */
public class ServicioDTO {
    //Atributos
    private Long id;
    private String tipo;
    private Boolean adicional;
    
    /**
     * Constructor por defecto
     */
    public ServicioDTO(){}
    
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
    
}
