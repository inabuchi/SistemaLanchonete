/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao.cliente;

import beans.cliente.ClienteBean;
import java.sql.Connection;
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
    
    public boolean inserirRegistro(ClienteBean cliente) throws ExceptionBD {
        List<FieldsAndValues> val = new ArrayList();
        val.add(new FieldsAndValues("cd_cliente", cliente.getCdCliente(), true));
        val.add(new FieldsAndValues("cd_pessoa", cliente.getCdPessoa(), true));
        val.add(new FieldsAndValues("ds_observacao", cliente.getDsObservacao()));
        val.add(new FieldsAndValues("ie_ativo", cliente.getIeAtivo()));                           
		
	return inserirRegistro(val);    
    }
    
    public boolean alterarRegistro(ClienteBean cliente) throws ExceptionBD {    
        List<FieldsAndValues> val = new ArrayList();
        val.add(new FieldsAndValues("cd_cliente", cliente.getCdCliente(), true));
        val.add(new FieldsAndValues("cd_pessoa", cliente.getCdCliente(), true));
        val.add(new FieldsAndValues("ds_observacao", cliente.getDsObservacao()));        
        val.add(new FieldsAndValues("ie_ativo", cliente.getIeAtivo()));        
        
        return alterarRegistro(val);
    }

    public boolean deletarRegistro(ClienteBean cliente) throws ExceptionBD { 
        List<FieldsAndValues> val = new ArrayList<>();
        val.add(new FieldsAndValues("cd_cliente", cliente.getCdCliente(), true));
        val.add(new FieldsAndValues("cd_pessoa", cliente.getCdCliente(), true));
        
        return deletarRegistro(val);
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
