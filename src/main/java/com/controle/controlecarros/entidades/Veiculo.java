package com.controle.controlecarros.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull(message = "{marca.not.null}")
    @NotEmpty(message = "{marca.not.empty}")
    private String marca;

    @NotNull(message = "{modelo_veiculo.not.null}")
    @NotEmpty(message = "{modelo_veiculo.not.empty}")
    private String modelo_veiculo;

    @NotNull(message = "{ano.Not.Null}")
    @NotEmpty(message = "{ano.Not.Empty}")
    private String ano;

    private String valor;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_cpf")
    private Usuario usuario;

    @Transient
    private String diaDeRotacao;

    @Transient
    private boolean rotacaoAtiva;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDiaDeRotacao() {
        this.setDiaDeRotacao();
        return this.diaDeRotacao;
    }

    public void setDiaDeRotacao() {
        char fim = this.ano.charAt(this.ano.length() - 1);
        if (fim == '0' || fim == '1') {
            this.diaDeRotacao = "Segunda-Feira";
        } else if (fim == '2' || fim == '3') {
            this.diaDeRotacao = "Terça-Feira";
        } else if (fim == '4' || fim == '5') {
            this.diaDeRotacao = "Quarta-Feira";
        } else if (fim == '6' || fim == '7') {
            this.diaDeRotacao = "Quinta-Feira";
        } else if (fim == '8' || fim == '9') {
            this.diaDeRotacao = "Sexta-Feira";
        }
    }

    public boolean getRotacaoAtiva() {
        this.setRotacaoAtiva();
        return this.rotacaoAtiva;
    }

    public void setRotacaoAtiva() {
        char fim = this.ano.charAt(this.ano.length() - 1);
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_WEEK);

        switch (dia) {
            case Calendar.MONDAY:
                if (fim == '0' || fim == '1') {
                    this.rotacaoAtiva = true;
                }
                break;
            case Calendar.TUESDAY:
                if (fim == '2' || fim == '3') {
                    this.rotacaoAtiva = true;
                }
                break;
            case Calendar.WEDNESDAY:
                if (fim == '4' || fim == '5') {
                    this.rotacaoAtiva = true;
                }
                break;
            case Calendar.THURSDAY:
                if (fim == '6' || fim == '7') {
                    this.rotacaoAtiva = true;
                }
                break;
            case Calendar.FRIDAY:
                if (fim == '8' || fim == '9') {
                    this.rotacaoAtiva = true;
                }
                break;
            default:
                this.rotacaoAtiva = false;
        }
    }

    

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo_veiculo, String ano, String valor, Usuario usuario) {
        super();
        this.marca = marca;
        this.modelo_veiculo = modelo_veiculo;
        this.ano = ano;
        this.valor = valor;
        this.usuario = usuario;
        this.setDiaDeRotacao();
		this.setRotacaoAtiva();
    }

}
