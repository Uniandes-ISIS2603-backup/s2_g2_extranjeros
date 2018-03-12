/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;


/**
 *
 * @author am.quintero12
 */
public class EstudianteDTO  {
    
    // ATRIBUTOS DE LA CLASE 
    /**
     * Nombre del estudiante 
     */
    private String nombre;
    
    /**
     * 
     */
    private Boolean estadoArrendamiento;
    
    private String usuario;
    private String clave;
    private String correo;
    private String celular;
    private String cedula;
    private int edad;
    
    /**
   
    
    //_______________________________________________
    //METODOS 
    //_______________________________________________
    
    //Constructor
    /**
     * Constructor
     * Empty por defecto
     */
    public EstudianteDTO(){
        
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param city: Es la entidad que se va a convertir a DTO
     */
    public EstudianteDTO(EstudianteEntity estudiante) {
     
        this.nombre = estudiante.getNombre();
       this.usuario = estudiante.getUsuario();
       this.clave = estudiante.getClave();
       this.cedula = estudiante.getCedula();
       this.celular = estudiante.getCelular();
       this.estadoArrendamiento = estudiante.isEstadoArrendamiento();
       this.edad = this.getEdad();
       this.correo = estudiante.getCorreo();

    }
    
    public EstudianteEntity toEntity(){
      return  new EstudianteEntity();
       
  
    }
            
 

 
    public String getNombre() {
        return nombre;
    }

    public Boolean getEstadoArrendamiento() {
        return estadoArrendamiento;
    }

 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadoArrendamiento(Boolean estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }
         
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
     * @param cedula El nuevo correo. 
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }   
    
    /**
     * @return La edad de un Usuario.
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Crea o modifica la edad asociado a un usuario.
     * @param edad La nueva edad. 
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }  
    
}
