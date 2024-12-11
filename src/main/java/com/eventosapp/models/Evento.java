package com.eventosapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Evento {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

//    @NotEmpty(message = "Campo nome não pode ser nulo.")
//    @Size(max = 2)
    @NotEmpty
    private String nome;
    @NotEmpty
    private String local;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    @NotEmpty
    private String hora;

    @OneToMany
    private List<Convidado> convidados;

    public Evento() {}

    public Evento(String nome, String local, Date data, String hora) {
        this.nome = nome;
        this.local = local;
        this.data = data;
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setCodigo(Long codigo) {this.codigo = codigo;}
    public Long getCodigo() {return codigo;}
}
