package com.karen.trabalhofinal.Model;

public class StatusDeputado {

    public long id;
    public String uri;
    public String nome;
    public String siglaPartido;
    public String uriPartido;
    public String siglaUf;
    public long idLegislatura;
    public String urlFoto;
    public String nomeEleitoral;
    public Gabinete gabinete;
    public String situacao;
    public String condicaoEleitoral;
    public String descricaoStatus;


    public StatusDeputado(long id, String uri, String nome, String siglaPartido, String uriPartido, String siglaUf, long idLegislatura, String urlFoto, String nomeEleitoral, Gabinete gabinete, String situacao, String condicaoEleitoral, String descricaoStatus) {
        this.id = id;
        this.uri = uri;
        this.nome = nome;
        this.siglaPartido = siglaPartido;
        this.uriPartido = uriPartido;
        this.siglaUf = siglaUf;
        this.idLegislatura = idLegislatura;
        this.urlFoto = urlFoto;
        this.nomeEleitoral = nomeEleitoral;
        this.gabinete = gabinete;
        this.situacao = situacao;
        this.condicaoEleitoral = condicaoEleitoral;
        this.descricaoStatus = descricaoStatus;
    }
}
