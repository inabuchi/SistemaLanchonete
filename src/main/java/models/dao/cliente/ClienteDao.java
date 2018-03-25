/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao.cliente;

import beans.cliente.ClienteBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.conexao.Conexao;
import models.conexao.EErrosBD;
import models.conexao.ExceptionBD;
import model.dao.utilitario.ObjectDAO;
import model.dao.utilitario.FieldsAndValues;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class ClienteDao extends ObjectDAO{

/*    public boolean Insert(ClienteBean cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlInsert;

        try {
            sqlInsert = "INSERT INTO cliente (";
            sqlInsert += " cd_cliente,";
            sqlInsert += " cd_pessoa,";
            sqlInsert += " ds_observacao,";
            sqlInsert += " ie_ativo";
            sqlInsert += " ) VALUES (";
            sqlInsert += cliente.getCdCliente();
            sqlInsert += cliente.getCdPessoa();
            sqlInsert += cliente.getDsObservacao();
            sqlInsert += cliente.getIeAtivo();
            sqlInsert += ");";

            PreparedStatement pst = conexao.prepareStatement(sqlInsert);

            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.INSERE_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }*/
    
    public boolean inserirRegistro(ClienteBean cliente) throws ExceptionBD {
        List<FieldsAndValues> val = new ArrayList();
        val.add(new FieldsAndValues("cd_cliente", cliente.getCdCliente()));
        val.add(new FieldsAndValues("cd_pessoa", cliente.getCdPessoa()));
        val.add(new FieldsAndValues("ds_observacao", cliente.getDsObservacao()));
        val.add(new FieldsAndValues("ie_ativo", cliente.getIeAtivo()));                           
		
	return inserirRegistro(val);    
    }

    public boolean Update(ClienteBean cliente) throws ExceptionBD {
        Connection conexao = Conexao.getConexao();
        String sqlUpdate;
        try {
            sqlUpdate = "UPDATE cliente SET";
            sqlUpdate += " cd_cliente = " + cliente.getCdCliente();
            sqlUpdate += " cd_pessoa = " + cliente.getCdPessoa();
            sqlUpdate += " ds_observacao = " + cliente.getDsObservacao();
            sqlUpdate += " ie_ativo = " + cliente.getIeAtivo();
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
            sqlDelete = "DELETE FROM cliente ";
            sqlDelete += "WHERE cd_cliente = " + Cliente.getCdCliente();

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
            sqlSelect += " cd_cliente";
            sqlSelect += " ,cd_pessoa";
            sqlSelect += " ,ds_observacao";
            sqlSelect += " ,ie_ativo";
            //sqlSelect += " WHERE cnCliente = " + Cliente.getCdCliente();

            //if (Cliente.getCdPessoa() != 0) {
            //    sqlSelect += " AND cd_pessoa = " + Cliente.getCdPessoa();
            //}

            //if (Cliente.getDsObservacao() != null || Cliente.getDsObservacao() != "") {
            //    sqlSelect += " AND ds_observacao = " + Cliente.getDsObservacao();
            //}

            //if (Cliente.getIeAtivo() != null || Cliente.getIeAtivo() != "") {
            //    sqlSelect += " AND ie_ativo = " + Cliente.getIeAtivo();
            //}
            sqlSelect += " from cliente;";

            ResultSet rs = st.executeQuery(sqlSelect);
            List<ClienteBean> clientes = new ArrayList<ClienteBean>();
            while (rs.next()) {
                clientes.add(new ClienteBean(
                        rs.getInt("cd_cliente"),
                        rs.getInt("cd_pessoa"),
                        rs.getString("ds_observacao"),
                        rs.getString("ie_ativo")));
            }
            return clientes;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.CONSULTA_DADO);
        } finally {
            Conexao.fechaConexao();
        }
    }
}
