/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author jr.pacheco10
 */

@Entity
public class CuentaBancariaEntity extends BaseEntity implements Serializable{

    //---------------------------------------------------
    // Atributos Relacionales
    //---------------------------------------------------
    
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private ArrendatarioEntity arrendatarioTitular;
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private Long numeroCuenta;
    private Double saldoCuenta;
    private String tipoCuenta;
    private String bancoAsociado;

    //---------------------------------------------------
    // Metodos Relacionales
    //---------------------------------------------------

    /**
     * @return Obtiene el Arrendatario Titular de la Cuenta de Banco.
     */
    public ArrendatarioEntity getArrendatarioTitular() {
        return arrendatarioTitular;
    }

    /**
     * Le asgina un tituar a una Cuenta Bancaria.
     * @param arrendatarioTitular Titular de cuenta que se asociara.
     */
    public void setArrendatarioTitular(ArrendatarioEntity arrendatarioTitular) {
        this.arrendatarioTitular = arrendatarioTitular;
    }
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
   
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
    public Double getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Le asgina un saldo a una Cuenta Bancaria.
     * @param saldoCuenta Saldo que se asociara.
     */
    public void setSaldoCuenta(Double saldoCuenta) {
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
