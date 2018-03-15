/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Douglas
 */
public class ClienteBean {

    private int cnCliente;
    private String dsNome;
    private String dsEndereco;
    private String caTelefone;

    public ClienteBean() {
        this(0, "", "", "");
    }

    public ClienteBean(int cnCliente, String dsNome, String dsEndereco, String caTelefone) {
        setCnCliente(cnCliente);
        setDsNome(dsNome);
        setDsEndereco(dsEndereco);
        setCaTelefone(caTelefone);
    }

    public int getCnCliente() {
        return cnCliente;
    }

    public void setCnCliente(int cnCliente) {
        this.cnCliente = cnCliente;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getCaTelefone() {
        return caTelefone;
    }

    public void setCaTelefone(String caTelefone) {
        this.caTelefone = caTelefone;
    }
}
