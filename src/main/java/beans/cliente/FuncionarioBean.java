/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.cliente;

/**
 *
 * @author Douglas
 */
public class FuncionarioBean {
    private int cdCliente;
    private int cdPessoa;
    private String dsObservacao;
    private String ieAtivo;

    public FuncionarioBean() {
        this(0, 0, "", "");
    }

    public FuncionarioBean(int cdCliente, int cdPessoa, String dsObservacao, String ieAtivo) {
        setCdCliente(cdCliente);
        setCdPessoa(cdPessoa);
        setDsObservacao(dsObservacao);
        setIeAtivo(ieAtivo);
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public int getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(int cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public String getIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(String ieAtivo) {
        this.ieAtivo = ieAtivo;
    }
}
