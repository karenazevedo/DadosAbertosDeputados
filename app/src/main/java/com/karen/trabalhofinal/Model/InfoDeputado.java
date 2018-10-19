package com.karen.trabalhofinal.Model;

import java.util.Date;
import java.util.List;

public class InfoDeputado {

    public long id;
    public String uri;
    public String nomeCivil;
    public StatusDeputado ultimoStatus;
    public String cpf;
    public char sexo;
    public String urlWebsite;
    public List<String> redeSocial;
    public String dataNascimento;
    public Date dataFalecimento;
    public String ufNascimento;
    public String municipioNascimento;
    public String escolaridade;


    public InfoDeputado(String nomeCivil, String dataNascimento, String municipioNascimento, String ufNascimento, String escolaridade) {
        this.nomeCivil = nomeCivil;
        this.dataNascimento = dataNascimento;
        this.municipioNascimento = municipioNascimento;
        this.ufNascimento = ufNascimento;
        this.escolaridade = escolaridade;
    }
}
