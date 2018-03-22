/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import beans.ClienteBean;
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
public class ClienteDao {

    public boolean Insert(ClienteBean cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlInsert;

        try {
            sqlInsert = "INSERT INTO Cliente (";
            sqlInsert += " cnCliente,";
            sqlInsert += " dsNome,";
            sqlInsert += " dsEndereco,";
            sqlInsert += " cnTelefone";
            sqlInsert += " ) VALUES (";
            sqlInsert += cliente.getCnCliente();
            sqlInsert += cliente.getDsNome();
            sqlInsert += cliente.getDsEndereco();
            sqlInsert += cliente.getCaTelefone();
            sqlInsert += ");";

            PreparedStatement pst = conexao.prepareStatement(sqlInsert);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.INSERE_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean Update(ClienteBean cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlUpdate;
        try {
            sqlUpdate = "UPDATE Cliente SET";
            sqlUpdate += " cnCliente = " + cliente.getCnCliente();
            sqlUpdate += " dsNome = " + cliente.getDsNome();
            sqlUpdate += " dsEndereco = " + cliente.getDsEndereco();
            sqlUpdate += " cnTelefone = " + cliente.getCaTelefone();
            sqlUpdate += ";";

            PreparedStatement pst = conexao.prepareStatement(sqlUpdate);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.ATUALIZA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean Delete(ClienteBean Cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlDelete;

        try {
            sqlDelete = "DELETE FROM Cliente ";
            sqlDelete += "WHERE cnCliente = " + Cliente.getCnCliente();

            PreparedStatement pst = conexao.prepareStatement(sqlDelete);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.EXCLUI_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public List<ClienteBean> DefaultSelect(ClienteBean Cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlSelect;
        try {
            Statement st = conexao.createStatement();

            sqlSelect = "SELECT";
            sqlSelect += " cnCliente";
            sqlSelect += " ,dsNome";
            sqlSelect += " ,dsEndereco";
            sqlSelect += " ,cnTelefone";
            sqlSelect += " WHERE cnCliente = " + Cliente.getCnCliente();

            if (Cliente.getDsNome() != null || Cliente.getDsNome() != "") {
                sqlSelect += " AND dsNome = " + Cliente.getDsNome();
            }

            if (Cliente.getDsEndereco() != null || Cliente.getDsEndereco() != "") {
                sqlSelect += " AND dsEndereco = " + Cliente.getDsEndereco();
            }

            if (Cliente.getCaTelefone() != null || Cliente.getCaTelefone() != "") {
                sqlSelect += " AND cnTelefone = " + Cliente.getCaTelefone();
            }
            sqlSelect += " from Cliente;";

            ResultSet rs = st.executeQuery(sqlSelect);
            List<ClienteBean> clientes = new ArrayList<ClienteBean>();
            while (rs.next()) {
                clientes.add(new ClienteBean(
                        rs.getInt("cnCliente"),
                        rs.getString("dsNome"),
                        rs.getString("dsEndereco"),
                        rs.getString("cnTelefone")));
            }
            return clientes;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.CONSULTA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }

    public boolean GetAsExists(ClienteBean Cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlSelect = "";

        try {
            Statement st = conexao.createStatement();
            sqlSelect = "select 1";
            sqlSelect += " from Cliente ";
            sqlSelect += " where cnCliente = " + Cliente.getCnCliente();
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

}
