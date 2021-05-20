package com.controle.controlecarros.entidades;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Caracteristicas implements Serializable  {
    
    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private String ano;

    @JsonProperty("Valor")
    private String valor;



    
    public Caracteristicas() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
   
    
}
