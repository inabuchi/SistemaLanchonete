/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.cliente;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class ClienteBean extends PessoaBean {

    private int cdCliente;
    private String dsObservacao;

    public ClienteBean() {
        this(0,"","","", new Date(),"",0,"");
    }

    public ClienteBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2,
            Date dtCadastro, String ieAtivo, int cdCliente, String dsObservacao) {
        setCdCliente(cdCliente);
        setDsObservacao(dsObservacao);
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }
}
