/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import beans.LoginBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.conexao.Conexao;
import models.conexao.EErrosBD;
import models.conexao.ExceptionBD;

/**
 *
 * @author Douglas
 */
public class LoginDao {
    public boolean Insert(LoginBean login) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlInsert;

        try {
            sqlInsert = "INSERT INTO Login (";
            sqlInsert += " cnLogin,";
            sqlInsert += " dsUsuario,";
            sqlInsert += " dsSenha,";
            sqlInsert += " ) VALUES (";
            sqlInsert += login.getCnLogin();
            sqlInsert += login.getDsUsuario();
            sqlInsert += login.getDsSenha();
            sqlInsert += ");";

            PreparedStatement pst = conexao.prepareStatement(sqlInsert);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.INSERE_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean Update(LoginBean login) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlUpdate;
        try {
            sqlUpdate = "UPDATE Login SET";
            sqlUpdate += " cnLogin = " + login.getCnLogin();
            sqlUpdate += " dsUsuario = " + login.getDsUsuario();
            sqlUpdate += " dsSenha = " + login.getDsSenha();
            sqlUpdate += ";";

            PreparedStatement pst = conexao.prepareStatement(sqlUpdate);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.ATUALIZA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean Delete(LoginBean login) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlDelete;

        try {
            sqlDelete = "DELETE FROM Login ";
            sqlDelete += "WHERE cnLogin = " + login.getCnLogin();

            PreparedStatement pst = conexao.prepareStatement(sqlDelete);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.EXCLUI_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public List<LoginBean> DefaultSelect(LoginBean login) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlSelect;
        try {
            Statement st = conexao.createStatement();

            sqlSelect = "SELECT";
            sqlSelect += " cnLogin";
            sqlSelect += " ,dsUsuario";
            sqlSelect += " ,dsSenha";
            sqlSelect += " WHERE cnLogin = " + login.getCnLogin();

            if (login.getDsUsuario() != null || login.getDsUsuario() != "") {
                sqlSelect += " AND dsUsuario = " + login.getDsUsuario();
            }

            if (login.getDsSenha() != null || login.getDsSenha() != "") {
                sqlSelect += " AND dsSenha = " + login.getDsSenha();
            }
            
            sqlSelect += " from Login;";

            ResultSet rs = st.executeQuery(sqlSelect);
            List<LoginBean> logins = new ArrayList<LoginBean>();
            while (rs.next()) {
                logins.add(new LoginBean(
                        rs.getInt("cnLogin"),
                        rs.getString("dsUsuario"),
                        rs.getString("cnSenha")));
            }
            return logins;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.CONSULTA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean GetAsExists(LoginBean login) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlSelect = "";

        try {
            Statement st = conexao.createStatement();
            sqlSelect = "select 1";
            sqlSelect += " from Login ";
            sqlSelect += " where cnLogin = " + login.getCnLogin();
            sqlSelect += " ;";

            ResultSet rs = st.executeQuery(sqlSelect);

            if (rs != null) //Registro não encontrado
            {
                return true;
            }
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.CONSULTA_DADO);
        } finally {
            Conexao.fechaConexao();
        }

        return false;
    }
    
    public int NextValue() throws ExceptionBD{
        Connection conexao = Conexao.getConexao();
        String sqlSelect = "";
        
        try {
            Statement st = conexao.createStatement();
            sqlSelect = "select max(cnLogin) + 1";
            sqlSelect += " from Login;";
            
            ResultSet rs = st.executeQuery(sqlSelect);
            if (rs == null){
                return 0;
            } else {
                return rs.getInt(1);
            }            
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.CONSULTA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }
}
