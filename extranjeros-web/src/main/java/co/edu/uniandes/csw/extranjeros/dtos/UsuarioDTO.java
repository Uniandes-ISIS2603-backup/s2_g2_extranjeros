/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuarios. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "username": string,
 *      "password: string,
 *      "rol": string
 *   }
 * </pre>
 * Por ejemplo una reseña se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "username": Juan Pérez,
 *      "password: 123456,
 *      "rol": admin
 *   }
 *
 * </pre>
 * @author ISIS2603
 */
public class UsuarioDTO {

    private String UserName;
    private String Contrasenia;
    private String rol;

    public UsuarioDTO() {

    }

    /**
     * @return the password
     */
    public String getPassword() {
        return Contrasenia;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.Contrasenia = password;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return UserName;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.UserName = username;
    }
}
