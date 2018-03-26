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
public class FuncionarioBean extends PessoaBean {
    private PessoaBean pessoa;
    private CargoBean cargo;
    private int cdCliente;
    private String dsObservacao;
    
    public FuncionarioBean() {
        this(0,"","","", new Date(),0,"","", 0, "");
    }

    public FuncionarioBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2,
            Date dtCadastro, int cdCargo, String dsCargo, String ieAtivo, int cdCliente, String dsObservacao) {
        setCdCliente(cdCliente);
        setDsObservacao(dsObservacao);
    }
    
    public PessoaBean getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(PessoaBean pessoa){
        this.pessoa = pessoa;
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
