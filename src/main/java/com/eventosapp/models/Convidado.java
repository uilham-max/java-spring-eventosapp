package com.eventosapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Convidado {

    @Id
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;

    public Evento getEvento() {return evento;}
    public void setEvento(Evento evento) {this.evento = evento;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getNomeConvidado() {return nomeConvidado;}
    public void setNomeConvidado(String nomeConvidado) {this.nomeConvidado = nomeConvidado;}

}
