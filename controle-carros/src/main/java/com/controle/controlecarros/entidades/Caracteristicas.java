package com.controle.controlecarros.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "caracteristicas")
public class Caracteristicas implements Serializable  {
    
    @Id
    private Long id;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private String ano;

    @JsonProperty("Valor")
    private String valor;

    //retorno api fipe
    private String nome;
    private String codigo;
    
    private ArrayList<Object> modelos = new ArrayList<Object>();
    private ArrayList<Object> anos = new ArrayList<Object>();
    //retorno api fipe

   
    
    public ArrayList<Object> getAnos() {
        return anos;
    }
    public void setAnos(ArrayList<Object> anos) {
        this.anos = anos;
    }
    public ArrayList<Object> getModelos() {
        return modelos;
    }
    public void setModelos(ArrayList<Object> modelos) {
        this.modelos = modelos;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
