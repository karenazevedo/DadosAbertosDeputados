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
    public Date dataNascimento;
    public Date dataFalecimento;
    public String ufNascimento;
    public String municipioNascimento;
    public String escolaridade;

    public InfoDeputado(long id, String uri, String nomeCivil, StatusDeputado ultimoStatus, String cpf, char sexo, String urlWebsite, List<String> redeSocial, Date dataNascimento, Date dataFalecimento, String ufNascimento, String municipioNascimento, String escolaridade) {
        this.id = id;
        this.uri = uri;
        this.nomeCivil = nomeCivil;
        this.ultimoStatus = ultimoStatus;
        this.cpf = cpf;
        this.sexo = sexo;
        this.urlWebsite = urlWebsite;
        this.redeSocial = redeSocial;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFalecimento;
        this.ufNascimento = ufNascimento;
        this.municipioNascimento = municipioNascimento;
        this.escolaridade = escolaridade;
    }

    public InfoDeputado(String nomeCivil, Date dataNascimento, String municipioNascimento, String ufNascimento, String escolaridade) {
        this.nomeCivil = nomeCivil;
        this.dataNascimento = dataNascimento;
        this.municipioNascimento = municipioNascimento;
        this.ufNascimento = ufNascimento;
        this.escolaridade = escolaridade;
    }
}
