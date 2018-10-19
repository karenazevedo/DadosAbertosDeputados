package com.karen.trabalhofinal.Model;

import java.util.Date;

public class Despesa {

    public long ano;
    public String cnpjCpfFornecedor;
    public String dataDocumento;
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

    public Despesa(Long valorDocumento, Long valorLiquido, String tipoDespesa, String dataDocumento) {
        this.valorDocumento = valorDocumento;
        this.valorLiquido = valorLiquido;
        this.tipoDespesa = tipoDespesa;
        this.dataDocumento = dataDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

}
