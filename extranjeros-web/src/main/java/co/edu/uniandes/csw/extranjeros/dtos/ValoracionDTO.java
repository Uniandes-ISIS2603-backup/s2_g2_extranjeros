/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;

/**
 * ValoracionDTO Objeto que representa una valoracion de una vivienda. 
 * 
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *    "id": number,
 *    "valoracion": number,
 *    "comentario": string,
 *   
 *  }
 * </pre>
 * Por ejemplo una valoracion se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *   "id": 3124,
 *    "valoracion": 3,
 *    "comentario": "habia mucho ruido",
 *
 *  }
 *
 * </pre>
 * @author jd.arango
 */
public class ValoracionDTO {
    private long id;
    
    private double valoracion;
    
    private String comentario;

    public ValoracionDTO(){
        
    }
    
    /**
     * @return the valoracion
     */
    public double getValoracion() {
        return valoracion;
    }

    /**
     * @param valoracion the valoracion to set
     */
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
          
    public ValoracionEntity toEntity(){
        ValoracionEntity e = new ValoracionEntity();
        e.setId(id);
        e.setValoracion(valoracion);
        return e;
    }
            
}
