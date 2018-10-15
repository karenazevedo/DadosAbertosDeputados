package com.karen.trabalhofinal.Model;

public class Gabinete {

    public long nome;
    public long predio;
    public long sala;
    public long andar;
    public String telefone;
    public String email;

    public Gabinete(long nome, long predio, long sala, long andar, String telefone, String email) {
        this.nome = nome;
        this.predio = predio;
        this.sala = sala;
        this.andar = andar;
        this.telefone = telefone;
        this.email = email;
    }
}
