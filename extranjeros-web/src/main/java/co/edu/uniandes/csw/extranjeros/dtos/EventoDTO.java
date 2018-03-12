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
 *      "nombreEvento": string,
 *      "tipoEvento": string,
 *      "fechaEvento": string,
 *      "ubicacionLon": string,
 *      "ubicacionLat": string,
 *      "privado": boolean,
 *      "distanciaVivienda": int,
 *      "capacidad": int
 *   }
 * </pre>
 * Por ejemplo una Evento se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombreEvento": "fiesta",
 *      "tipoEvento": "fiesta",
 *      "fechaEvento": "12/12/2018 15:00",
 *      "ubicacionLon": "1.59382829",
 *      "ubicacionLat": "-80.4938392",
 *      "privado": true,
 *      "distanciaVivienda": 20,
 *      "capacidad": 200
 *   }
 *
 * </pre>
 * @author la.ruiz967
 */
public class EventoDTO {

    /**
     * nombre del evento
     */
    private String nombreEvento;
    
    /**
     * tipo del evento
     */
    private String tipoEvento;
    
    /**
     * fecha del evento
     */
    private String fechaEvento;
    
    /**
     * ubicación del evento en longitud
     */
    private String ubicacionLon;
    
    /**
     * ubicación del evento en latitud
     */
    private String ubicacionLat;
    
    /**
     * distancia de la vivienda al evento
     */
    private Integer distanciaVivienda;
    
    /**
     * Indica si el evento es privado o no
     */
    private Boolean privado;
    
    /**
     * Capacidad del evento
     */
    private Integer capacidad;
    
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
            this.distanciaVivienda = entity.getDistanciaVivienda();
            this.id = entity.getId();
        }
    }

    /**
     * Retorna el nombre del evento
     * @return 
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * Cambia el nombre del evento
     * @param nombreEvento
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * retorna el tipo del evento
     * @return 
     */
    public String getTipoEvento() {
        return tipoEvento;
    }

    /**
     * cambia el tipo del evento
     * @param tipoEvento 
     */
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    /**
     * retorna la fecha del evento
     * @return 
     */
    public String getFechaEvento() {
        return fechaEvento;
    }

    /**
     * cambia la fecha del evento
     * @param fechaEvento
     */
    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * retorna la ubicación en longitud del evento
     * @return 
     */
    public String getUbicacionLon() {
        return ubicacionLon;
    }

    /**
     * cambia la longitud del evento
     * @param ubicacionLon
     */
    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }

    /**
     * retorna la ubicación en latitud del evento
     * @return 
     */
    public String getUbicacionLat() {
        return ubicacionLat;
    }

    /**
     * cambia la latitud del evento
     * @param ubicacionLat
     */
    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    /**
     * retorna la distancia a la vivienda del evento
     * @return 
     */
    public Integer getDistanciaVivienda() {
        return distanciaVivienda;
    }

    /**
     * cambia la distancia del evento
     * @param distanciaVivienda
     */
    public void setDistanciaVivienda(Integer distanciaVivienda) {
        this.distanciaVivienda = distanciaVivienda;
    }

    /**
     * retorna la privacidad del evento
     * @return 
     */
    public Boolean isPrivado() {
        return privado;
    }

    /**
     * cambia la privacidad del evento
     * @param privado
     */
    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    /**
     * retorna la capacidad del evento
     * @return 
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * cambia la capacidad del evento
     * @param capacidad
     */
    public void setCapacidad(Integer capacidad) {
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
