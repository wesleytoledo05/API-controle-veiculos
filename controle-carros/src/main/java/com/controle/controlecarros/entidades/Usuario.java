package com.controle.controlecarros.entidades;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @NotNull(message = "{cpf.not.null}")
    @NotEmpty(message = "{cpf.not.empty}")
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @NotNull(message = "{nome.not.null}")
    @NotEmpty(message = "{nome.not.empty}")
    private String nome;

    @Email
    @NotNull(message = "{email.not.null}")
    @NotEmpty(message = "{email.not.empty}")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "{data_nascimento.not.null}")
    private Date data_nascimento;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Veiculo> veiculo = new ArrayList<>();

    public Usuario(){
        
    }

    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
}


  