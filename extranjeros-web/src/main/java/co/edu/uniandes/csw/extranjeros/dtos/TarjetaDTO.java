/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.CityEntity;
import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;

/**
 * TarjetaDTO Objeto de transferencia de datos de Cities. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "numero: number,
 *      "banco": string,
 *      "fechaCaducidad: string
 *   }
 * </pre>
 * Por ejemplo una tarjeta se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "numero: 123456789,
 *      "banco": "davivienda",
 *      "fechaCaducidad: "2018/20/02"        
 *   }
 *
 * </pre>
 * @author ISIS2603
 */
public class TarjetaDTO {

    private Long id;
    private Long numero;
    private String banco;
    private String fechaCaducidad;

    /**
     * Constructor por defecto
     */
    public TarjetaDTO() {
    }
    
    /**
     * Constructor que recibe un TarjetaEntity
     */
    public TarjetaDTO(TarjetaEntity entity) {
        if(entity != null)
        {
            this.banco = entity.getBanco();
            this.numero = entity.getNumero();
            this.fechaCaducidad = entity.getFechaCaducidad();
            this.id = entity.getId();
        }

    }
    
    


    /**
     * @return El ID de la tarjeta
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El nuevo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return El nombre del banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param name El nuevo nombre
     */
    public void setBanco(String name) {
        this.banco = name;
    }

    /**
     * @return la fecha de caducidad
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * @param fecha La nueva fecha
     */
    public void setFechaCaducidad(String fecha) {
        this.fechaCaducidad = fecha;
    }
    
    /**
     * @return El numero de la tarjeta
     */
    public Long getNumero() {
        return numero;
    }
    
    /**
     * @param num El nuevo numero
     */
    public void setNumero(Long num) {
        this.numero = num;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TarjetaEntity toEntity() {
        TarjetaEntity entity = new TarjetaEntity();
        entity.setId(this.id);
        entity.setBanco(this.banco);
        entity.setFechaCaducidad(this.fechaCaducidad);
        entity.setNumero(this.numero);
        return entity;
    }
}
