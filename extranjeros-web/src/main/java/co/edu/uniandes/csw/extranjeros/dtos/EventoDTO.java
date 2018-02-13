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
 *      "responsableEventoP": string,
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
 *      "responsableEventoP": "pedro",
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

    private String responsableEventoP;
    private String nombreEvento;
    private String tipoEvento;
    private Date fechaEvento;
    private String ubicacionLon;
    private String ubicacionLat;
    private boolean privado;
    private int capacidad;
    private Long id;

    /**
     * Constructor por defecto
     */
    public EventoDTO() {
    }


    /**
     * @return El ID del evento
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
     * @return El responsable del evento
     */
    public String getResponsable() {
        return responsableEventoP;
    }

    /**
     * @param resp El nuevo responsable
     */
    public void setResponsable(String resp) {
        this.responsableEventoP = resp;
    }

    /**
     * @return la fecha del evento
     */
    public Date getFechaCaducidad() {
        return fechaEvento;
    }

    /**
     * @param fecha La nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fechaEvento = fecha;
    }
    
    /**
     * @return El nombre del evento
     */
    public String getNombre() {
        return nombreEvento;
    }
    
    /**
     * @param name El nuevo nombre
     */
    public void setNumero(String name) {
        this.nombreEvento = name;
    }
    
    /**
     * @return El tipo del evento
     */
    public String getTipo() {
        return tipoEvento;
    }
    
    /**
     * @param tipo El nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipoEvento = tipo;
    }
    
    /**
     * @return La ubicacion Lon del evento
     */
    public String getLon() {
        return ubicacionLon;
    }
    
    /**
     * @param lon La nueva ubicacion Lon
     */
    public void setLon(String lon) {
        this.ubicacionLon = lon;
    }
    
    /**
     * @return La ubicacion Lat del evento
     */
    public String getLat() {
        return ubicacionLat;
    }
    
    /**
     * @param lat La nueva ubicacion Lat
     */
    public void setLat(String lat) {
        this.ubicacionLat = lat;
    }
    
    /**
     * @return La privacidad del evento
     */
    public boolean getPrivacidad() {
        return privado;
    }
    
    /**
     * @param bool La nueva privacidad del evento
     */
    public void setPrivacidad(boolean bool) {
        this.privado = bool;
    }
    
    /**
     * @return La capacidad Lon del evento
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * @param cap La nueva capacidad del evento
     */
    public void setCapacidad(int cap) {
        this.capacidad = cap;
    }
    
    

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    //public CityEntity toEntity() {
    //    CityEntity entity = new CityEntity();
    //    entity.setId(this.id);
    //    entity.setName(this.banco);
    //    entity.setZipcode(this.fechaCaducidad);
    //    return entity;
    //}
}
