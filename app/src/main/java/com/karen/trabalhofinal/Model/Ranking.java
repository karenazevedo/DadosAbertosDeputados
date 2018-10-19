package com.karen.trabalhofinal.Model;

public class Ranking {

    public long idDeputado;
    public double despesa;
    public String tipoDespesa;
    public String dataDocumento;
    public String nome;


    public Ranking(long idDeputado, double despesa, String tipoDespesa, String dataDocumento, String nome) {
        this.idDeputado = idDeputado;
        this.despesa = despesa;
        this.tipoDespesa = tipoDespesa;
        this.dataDocumento = dataDocumento;
        this.nome = nome;
    }

    public Ranking() {}
}
