/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.ClienteBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.ClienteDao;

/**
 *
 * @author Douglas
 */
public class ClienteControllers {

    //ClienteBean cliente = new ClienteBean();
    ClienteDao clienteDao = new ClienteDao();

    public void ConvertStringToObject(String cnCliente, String dsNome, String dsEndereco, String caTelefone, int acao) throws Exception{
        try {
            int codigo = Integer.parseInt(cnCliente);
            String nome = dsNome;
            String endereco = dsEndereco;
            String telefone = caTelefone;
            
            ClienteBean cliente = new ClienteBean(codigo, nome, endereco, telefone);
            
            EAcao acaoView = EAcao.values()[acao];
            switch (acaoView){
                case INSERT:
                    this.Insert(cliente);
                    break;
                case UPDATE:
                    this.Update(cliente);
                    break;
                case DELETE:
                    this.Delete(cliente);
                    break;
                case DEFAULT_SELECT:
                    this.DefaultSelect(cliente);
                    break;
                case INSERT_OR_UPDATE: 
                    this.InsertOrUpdate(cliente);
                    break;
            }
            
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    
    public void Insert(ClienteBean cliente) throws Exception {

        try {
            clienteDao.Insert(cliente);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Update(ClienteBean cliente) throws Exception {

        try {
            clienteDao.Update(cliente);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Delete(ClienteBean cliente) throws Exception {

        try {
            clienteDao.Delete(cliente);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<ClienteBean> DefaultSelect(ClienteBean cliente) throws Exception {

        try {
            return clienteDao.DefaultSelect(cliente);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void InsertOrUpdate(ClienteBean cliente) throws ExceptionBD {
        if (clienteDao.GetAsExists(cliente)) {
            clienteDao.Update(cliente);
        } else {
            clienteDao.Insert(cliente);
        }
    }

}
