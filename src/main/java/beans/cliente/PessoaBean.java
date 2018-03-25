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
public class PessoaBean {
    private int cdPessoa;
    private String dsNome;
    private String dsTelefone1;
    private String dsTelefone2;
    private Date dtCadastro;
    private String ieAtivo;

    public PessoaBean() {
        this(0,"","","", new Date(), "");
    }

    public PessoaBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2, Date dtCadastro, String ieAtivo) {
        setCdPessoa(cdPessoa);
        setDsNome(dsNome);
        setDsTelefone1(dsTelefone1);
        setDsTelefone2(dsTelefone2);
        setDsCadastro(dtCadastro);
        setIeAtivo(ieAtivo);
    }

    public int getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(int cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsTelefone1() {
        return dsTelefone1;
    }

    public void setDsTelefone1(String dsTelefone1) {
        this.dsTelefone1 = dsTelefone1;
    }

    public String getDsTelefone2() {
        return dsTelefone1;
    }

    public void setDsTelefone2(String dsTelefone1) {
        this.dsTelefone1 = dsTelefone1;
    }
    
    public Date getDsCadastro() {
        return dtCadastro;
    }

    public void setDsCadastro(Date dsCadastro) {
        this.dtCadastro = dsCadastro;
    }
    
    public String getIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(String ieAtivo) {
        this.ieAtivo = ieAtivo;
    }
}
