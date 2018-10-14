package com.karen.trabalhofinal.Model;

import java.util.Date;

public class Despesa {

    public long ano;
    public String cnpjCpfFornecedor;
    public Date dataDocumento;
    public Long idDocumento;
    public Long idLote;
    public Long idTipoDocumento;
    public Long mes;
    public String nomeFornecedor;
    public String numDocumento;
    public String numRessarcimento;
    public Long parcela;
    public String tipoDespesa;
    public String tipoDocumento;
    public String urlDocumento;
    public Long valorDocumento;
    public Long valorGlosa;
    public Long valorLiquido;

    public Despesa(Long valorDocumento, Long valorLiquido, String tipoDespesa) {
        this.valorDocumento = valorDocumento;
        this.valorLiquido = valorLiquido;
        this.tipoDespesa = tipoDespesa;
    }

    public long getAno() {
        return ano;
    }

    public void setAno(long ano) {
        this.ano = ano;
    }

    public String getCnpjCpfFornecedor() {
        return cnpjCpfFornecedor;
    }

    public void setCnpjCpfFornecedor(String cnpjCpfFornecedor) {
        this.cnpjCpfFornecedor = cnpjCpfFornecedor;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Long getMes() {
        return mes;
    }

    public void setMes(Long mes) {
        this.mes = mes;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNumRessarcimento() {
        return numRessarcimento;
    }

    public void setNumRessarcimento(String numRessarcimento) {
        this.numRessarcimento = numRessarcimento;
    }

    public Long getParcela() {
        return parcela;
    }

    public void setParcela(Long parcela) {
        this.parcela = parcela;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public Long getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(Long valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public Long getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(Long valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public Long getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Long valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

}
