/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;

/**
 * ArrendatarioDTO Objeto de transferencia de datos de Arrendatario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "id": number
 *   }
 * </pre>
 * Por ejemplo una Arrendatario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Carlos Hugo" 
 *      "id": 4323
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */
public class ArrendatarioDTO {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private String usuario;
    private String clave;
    private String correo;
    private String celular;
    private String cedula;
    private int edad;
    private String nombre;
    private Long id;
    
    private String imagen;
    private String rol;
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public ArrendatarioDTO(){
        /* Constructor vacío por defecto*/
    }
    
    /**
     * Crea un objeto UsuarioTO a partir de un objeto UsuarioEntity.
     * @param entity Entidad UsuarioEntity desde la cual se va a crear el nuevo
     * objeto.
     */
        public ArrendatarioDTO (ArrendatarioEntity entity) {
        if (entity != null) {
            
            // De Arrendatario
            this.nombre = entity.getNombre();
            
            // De Usuario
            this.id = entity.getId();
            this.usuario = entity.getUsuario();
            this.clave = entity.getClave();
            this.correo = entity.getCorreo();
            this.celular = entity.getCelular();
            this.cedula = entity.getCedula();
            this.edad = entity.getEdad();
            this.imagen = entity.getImagen();
            this.rol = "Arrendatario";
        }
    }
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

     /**
     * Convierte un objeto UsuarioDTO a UsuarioEntity.
     * @return Nueva objeto UsuarioEntity.
     */
    public ArrendatarioEntity toEntity() {
        
        // Genera
        ArrendatarioEntity entity = new ArrendatarioEntity();
        
        // Asocia atributos
        entity.setId(this.getId());
        entity.setNombre(this.nombre);
        
        entity.setUsuario(this.getUsuario());
        entity.setClave(this.getClave());
        entity.setCorreo(this.getCorreo());
        entity.setCelular(this.getCelular());
        entity.setEdad(this.edad);
        entity.setCedula(this.cedula);
        entity.setImagen(this.imagen);
        entity.setRol("Arrendatario");
        // Return
        return entity;
    }
    
    /**
     * @return Obtiene el nombre del arrendatario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Crea el nombre asociado a un usuario de tipo arrendatario
     * @param pNombre Nombre a asociar 
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

     /**
     * @return Retorna el ID de un Usuario. 
     */
    public Long getId() {
        return id;
    }

    /**
     * Le asgina un identificador a un Usuario
     * @param id Identificador que se asociara
     */
    public void setId(Long id) {
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
