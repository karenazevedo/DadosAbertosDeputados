package com.karen.trabalhofinal.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class Deputado {

    private long id;
    private String uri;
    private String nome;
    private String siglaPartido;
    private String uriPartido;
    private String siglaUf;
    private String urlFoto;
    private long idLegislatura;

    public Deputado(String nome, String siglaPartido, String foto) {

        this.nome = nome;
        this.siglaPartido = siglaPartido;
        this.urlFoto = foto;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public long getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(long idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    @Override
    public String toString() {

        return "Deputado{" +
                "name='" + nome + '\'' +
                ", siglaPartido=" + siglaPartido +
                '}';
    }

}
