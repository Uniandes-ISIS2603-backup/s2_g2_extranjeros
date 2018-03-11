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

import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import java.util.Date;

/**
 * EventoDTO Objeto de transferencia de datos de Cities. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombreEvento": string,
 *      "tipoEvento": string,
 *      "fechaEvento": Date,
 *      "ubicacionLon": string,
 *      "ubicacionLat": string,
 *      "privado": boolean,
 *      "capacidad": int
 *   }
 * </pre>
 * Por ejemplo una Evento se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 139028,
 *      "nombreEvento": "fiesta",
 *      "tipoEvento": "fiesta",
 *      "fechaEvento": Date,
 *      "ubicacionLon": "1.59382829",
 *      "ubicacionLat": "-80.4938392",
 *      "privado": true,
 *      "capacidad": 200        
 *   }
 *
 * </pre>
 * @author ISIS2603
 */
public class EventoDTO {

    private String nombreEvento;
    private String tipoEvento;
    private Date fechaEvento;
    private String ubicacionLon;
    private String ubicacionLat;
    private Integer distanciaVivienda;
    private boolean privado;
    private int capacidad;
    private Long id;

    /**
     * Constructor por defecto
     */
    public EventoDTO() {
    }
    
    /**
     * Constructor que recibe un EventoEntity
     * @param entity Entidad a convertir
     */
    public EventoDTO(EventoEntity entity) {
        if(entity != null)
        {
            this.nombreEvento = entity.getNombreEvento();
            this.tipoEvento = entity.getTipoEvento();
            this.fechaEvento = entity.getFechaEvento();
            this.ubicacionLat = entity.getUbicacionLat();
            this.ubicacionLon = entity.getUbicacionLon();
            this.privado = entity.isPrivado();
            this.capacidad = entity.getCapacidad();
            this.id = entity.getId();
        }
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getUbicacionLon() {
        return ubicacionLon;
    }

    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }

    public String getUbicacionLat() {
        return ubicacionLat;
    }

    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    public Integer getDistanciaVivienda() {
        return distanciaVivienda;
    }

    public void setDistanciaVivienda(Integer distanciaVivienda) {
        this.distanciaVivienda = distanciaVivienda;
    }

    public boolean isPrivado() {
        return privado;
    }

    public void setPrivado(boolean privado) {
        this.privado = privado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


    
    

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public EventoEntity toEntity() {
        EventoEntity entity = new EventoEntity();
        entity.setId(this.id);
        entity.setCapacidad(this.capacidad);
        entity.setDistanciaVivienda(this.distanciaVivienda);
        entity.setFechaEvento(this.fechaEvento);
        entity.setNombreEvento(this.nombreEvento);
        entity.setPrivado(this.privado);
        entity.setTipoEvento(this.tipoEvento);
        entity.setUbicacionLat(this.ubicacionLat);
        entity.setUbicacionLon(this.ubicacionLon);
        return entity;
    }
}
