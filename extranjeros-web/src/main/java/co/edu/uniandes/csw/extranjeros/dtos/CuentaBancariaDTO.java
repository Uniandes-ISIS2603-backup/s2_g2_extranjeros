/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;

/**
 * CuentaBancariaDTO Objeto de transferencia de datos de Cuenta Bancaria. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "numeroCuenta": number,
 *      "saldoCuenta": number,
 *      "tipoCuenta": String,
 *      "bancoAsociado": String,
 *      "id": number
 *   }
 * </pre>
 * Por ejemplo una Arrendatario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "numeroCuenta": 12312312312132,
 *      "saldoCuenta": 3333.333333,
 *      "tipoCuenta": Ahorros,
 *      "bancoAsociado": Banco Caja Social, 
 *      "id": 4323
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */
public class CuentaBancariaDTO {

    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private Long id;
    private Long numeroCuenta;
    private double saldoCuenta;
    private String tipoCuenta;
    private String bancoAsociado;

    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public CuentaBancariaDTO(){
        
    }
    
    /**
     * Crea un objeto CuentaBancariaDTO a partir de un objeto CuentaBancariaEntity.
     * @param entity Entidad CuentaBancariaEntity desde la cual se va a crear el nuevo
     * objeto.
     */
    public CuentaBancariaDTO (CuentaBancariaEntity entity) {
        
        if (entity != null) {
            
            this.id = entity.getId();
            this.numeroCuenta = entity.getNumeroCuenta();
            this.saldoCuenta = entity.getSaldoCuenta();
            this.tipoCuenta = entity.getTipoCuenta();
            this.bancoAsociado = entity.getBancoAsociado();
        }
    }

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

    /**
     * Convierte un objeto CuentaBancariaDTO a CuentaBancariaEntity.
     * @return Nueva objeto CuentaBancariaEntity.
     */
    public CuentaBancariaEntity toEntity() {

        // Genera
        CuentaBancariaEntity entity = new CuentaBancariaEntity();
        
        // Asocia atributos
        entity.setId(this.getId());
        entity.setNumeroCuenta(this.numeroCuenta);
        entity.setSaldoCuenta(this.saldoCuenta);
        entity.setTipoCuenta(this.tipoCuenta);
        entity.setBancoAsociado(this.bancoAsociado);
        
        // Return
        return entity;
    }
 
    /**
     * @return Obtiene el Identificador una Cuenta de Banco.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Le asgina un identificador a una Cuenta Bancaria.
     * @param id Identificador que se asociara.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Obtiene el numero de una Cuenta de Banco.
     */
    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Le asgina un numero a una Cuenta Bancaria.
     * @param numeroCuenta Numero de cuenta que se asociara.
     */
    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return Obtiene el saldo existente en una Cuenta de Banco.
     */
    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Le asgina un saldo a una Cuenta Bancaria.
     * @param saldoCuenta Saldo que se asociara.
     */
    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    /**
     * @return Obtiene el tipo de cuenta bancaria (Ahorros o Corriente).
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Le asgina un tipo a una Cuenta Bancaria.
     * @param tipoCuenta Tipo de cuenta que se asociara.
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * @return Obtiene el banco encargado de administrar la cuenta bancaria.
     */
    public String getBancoAsociado() {
        return bancoAsociado;
    }
    
    /**
     * Le asgina un banco a una Cuenta Bancaria.
     * @param bancoAsociado Banc que se asociara a una cuenta.
     */
    public void setBancoAsociado(String bancoAsociado) {
        this.bancoAsociado = bancoAsociado;
    }
}
