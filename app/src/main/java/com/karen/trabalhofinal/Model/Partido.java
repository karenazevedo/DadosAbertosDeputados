package com.karen.trabalhofinal.Model;

import java.util.Date;

public class Partido {

    public long id;
    public String sigla;;
    public String nome;
    public String uri;

    public Partido(String sigla, String nome, String uri) {
        this.sigla = sigla;
        this.nome = nome;
        this.uri = uri;
    }

}
