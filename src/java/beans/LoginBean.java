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
public class LoginBean {
    
    private int cnLogin;
    private String dsUsuario;
    private String dsSenha;
    
    public LoginBean() {
        this(0, "", "");
    }

    public LoginBean(int cnLogin, String dsUsuario, String dsSenha) {
        setCnLogin(cnLogin);
        setDsUsuario(dsUsuario);
        setDsSenha(dsSenha);
    }

    public int getCnLogin() {
        return cnLogin;
    }

    public void setCnLogin(int cnLogin) {
        this.cnLogin = cnLogin;
    }
    
    public String getDsUsuario() {
        return dsUsuario;
    }

    public void setDsUsuario(String dsUsuario) {
        this.dsUsuario = dsUsuario;
    }
    
    public String getDsSenha(){
        return dsSenha;
    }
    
    public void setDsSenha(String dsSenha){
        this.dsSenha = dsSenha;
    }
}
