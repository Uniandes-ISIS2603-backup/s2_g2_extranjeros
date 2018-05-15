/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import co.edu.uniandes.csw.extranjeros.podam.CelularStrategy;
import co.edu.uniandes.csw.extranjeros.podam.ClaveStrategy;
import co.edu.uniandes.csw.extranjeros.podam.CorreoStrategy;
import co.edu.uniandes.csw.extranjeros.podam.IntegerStrategy;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author am.quintero12
 */

@Entity
public class EstudianteEntity extends BaseEntity {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    private String nombre;
    private boolean estadoArrendamiento;
     private String usuario;
     private String imagen;
    
    @PodamStrategyValue(CorreoStrategy.class)
    private String correo;
    
    @PodamStrategyValue(ClaveStrategy.class)
    private String clave;
    
    @PodamStrategyValue(CelularStrategy.class)
    private String celular;
    
    private String cedula;
    
    @PodamStrategyValue(IntegerStrategy.class)
    private Integer edad;
    
    //---------------------------------------------------
    // Relaciones
    //---------------------------------------------------
   @PodamExclude
   @OneToOne
    private ProvidenciaEntity providencia;
   
   @PodamExclude
   @OneToOne (cascade = CascadeType.ALL)
   private TarjetaEntity tarjeta;
   
   @PodamExclude
   @OneToOne 
   private UniversidadEntity universidad;
   
   @PodamExclude
   @OneToMany(mappedBy = "responsableEventoP",cascade = CascadeType.ALL)
   private List<EventoEntity> eventosCreados;
   
   @PodamExclude
   @ManyToMany
   private List<EventoEntity> eventosInvitado;
   
   @PodamExclude
   @ManyToMany
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @ManyToOne
    private ViviendaEntity vivienda;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public ViviendaEntity getViviendas() {
        return vivienda;
    }

    public void setViviendas(ViviendaEntity viviendas) {
        this.vivienda = viviendas;
    }
    
    public TarjetaEntity getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaEntity tarjeta) {
        this.tarjeta = tarjeta;
    }

    public UniversidadEntity getUniversidad() {
        return universidad;
    }

    public void setUniversidad(UniversidadEntity universidad) {
        this.universidad = universidad;
    }

    public List<EventoEntity> getEventosCreados() {
        return eventosCreados;
    }

    public void addEventosCreados(EventoEntity evento) {
        this.eventosCreados.add(evento);
    }

    public List <EventoEntity> getEventosInvitado() {
        return eventosInvitado;
    }

    public void addEventosInvitado(EventoEntity evento) {
        this.eventosInvitado.add(evento);
    }
   
   

    public ProvidenciaEntity getProvidencia() {
        return providencia;
    }

    public void setProvidencia(ProvidenciaEntity providencia) {
        this.providencia = providencia;
    }
    
    
    
    /**
     * Método que retorna el nombre del estudiante
     * @return Nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del estudiante 
     * @param nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Método que retorna el estado de arrendaiento del estudiante 
     * @return El estado de arrendamiento del estudiante
     */
    public boolean isEstadoArrendamiento() {
        return estadoArrendamiento;
    }

    /**
     * Asigna el estado de arrentamiento
     * @param estadoArrendamiento 
     */
    public void setEstadoArrendamiento(Boolean estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }
    
    public void agregarEventoCreado(EventoEntity e)
    {
        this.eventosCreados.add(e);
    }
    public void agregarEventoInvitado(EventoEntity e)
    {
        this.eventosInvitado.add(e);
    }

    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    }

    //---------------------------------------------------
    // Metodos Anteriores a Usuario
    //---------------------------------------------------
    
    /**
     * @return El nombre (nickname en la plataforma) del usuario. 
     */
    public String getUsuario(){
        return usuario;
    }
    
    /**
     * Crea un nombre de usuario. 
     * @param newUser El nuevo ID
     */
    public void setUsuario(String newUser){
        this.usuario = newUser;
    }
    
    /**
     * @return La clave del usuario
     */
    public String getClave(){
        return clave;
    }
    
    /**
     * Crea o cambia una contrasenia. 
     * @param newPassword La nueva contrasenia.
     */
    public void setClave(String newPassword){
        this.clave = newPassword;
    }
    
    /**
     * @return El correo de un usuario.
     */
    public String getCorreo(){
        return correo;
    }
    
    /**
     * Crea o modifica el correo asociado a un usuario.
     * @param newEmail El nuevo correo. 
     */
    public void setCorreo(String newEmail){
        this.correo = newEmail;
    }
    
    /**
     * @return El numero de un usuario.
     */
    public String getCelular(){
        return celular;
    }
    
    /**
     * Crea o modifica el numero asociado a un usuario.
     * @param newPhone El nuevo correo. 
     */
    public void setCelular(String newPhone){
        this.celular = newPhone;
    }
    
    /**
     * @return El numero de cedula de un usuario.
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Crea o modifica el numero de cedula asociado a un usuario.
     * @param cedula La nueva cedula. 
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return La edad de un Usuario.
     */
    public Integer getEdad() {
        return edad;
    }
    
    /**
     * Crea o modifica la edad asociado a un usuario.
     * @param edad La nueva edad. 
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
