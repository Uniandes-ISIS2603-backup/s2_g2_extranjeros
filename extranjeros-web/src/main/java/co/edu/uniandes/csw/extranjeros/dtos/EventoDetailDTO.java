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
import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link EventoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la evento vaya a la documentacion de {@link EventoDTO}
 *  Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombreEvento": string,
 *      "tipoEvento": string,
 *      "fechaEvento": string,
 *      "ubicacionLon": string,
 *      "ubicacionLat": string,
 *      "privado": boolean,
 *      "distanciaVivienda": int,
 *      "capacidad": int,
 *      "responsableEventoP": {Estudiante},
 *      "estudiantes": [{Estudiante}, {Estudiante}]
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
 *      "capacidad": 200,
 *      "responsableEventoP": {},
 *      "estudiantes": []
 *   }
 *
 * </pre>
 * @author la.ruiz967
 */
public class EventoDetailDTO extends EventoDTO {
    
    /**
     * Lugar de interés 
     */
    private LugaresDeInteresDTO lugarDeInteres;
    
    /**
     * Lista de participantes del evento
     */
    private List<EstudianteDTO> estudiantes;
    
    /**
     * Responsable del evento
     */
    private EstudianteDTO responsableEventoP;

    /**
     * Constructor por defecto
     */
    public EventoDetailDTO() {
        super();
    }
    
    /**
     * Retorna el lugar de interés asociado
     * @return El lugar de interés del evento.
     */
    public LugaresDeInteresDTO getLugarDeInteres() {
        return lugarDeInteres;
    }

    /**
     * Modifica el lugar de interés
     * @param lugarDeInteres El nuevo lugar
     */
    public void setLugarDeInteres(LugaresDeInteresDTO lugarDeInteres) {
        this.lugarDeInteres = lugarDeInteres;
    }

    /**
     * Retorna la lista de estudiantes participantes del evento.
     * @return lista de asistentes.
     */
    public List<EstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Modifica la lista de asistentes
     * @param estudiantes La nueva lista de asistentes
     */
    public void setEstudiantes(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
    /**
     * Retorna el responsable del evento
     * @return Responsable del evento
     */
    private EstudianteDTO getResponsableEventoP()
    {
        return responsableEventoP;
    }
    
    /**
     * Cambia el responsable del evento
     * @param responsableEventoP El nuevo responsable
     */
    private void setResponsableEventoP(EstudianteDTO responsableEventoP)
    {
        this.responsableEventoP = responsableEventoP;
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad a partir de la cual se construye el objeto
     */
    public EventoDetailDTO(EventoEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public EventoEntity toEntity() {
        EventoEntity eventoE = super.toEntity();
        List<EstudianteEntity> lista = new ArrayList<>();
        if(estudiantes != null)
        {
            for(EstudianteDTO es: estudiantes)
            {
                lista.add(es.toEntity());
            }
        }
        
        eventoE.setEstudiantesInvitados(lista);
        if(responsableEventoP != null)
        {
           eventoE.setResponsableEventoP(responsableEventoP.toEntity()); 
        }
        if(lugarDeInteres != null)
        {
           eventoE.setLugarDeInteres(lugarDeInteres.toEntity()); 
        }
        return eventoE;
    }

}
