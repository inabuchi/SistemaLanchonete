/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import beans.cliente.ClienteBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.cliente.ClienteDao;

/**
 *
 * @author Douglas
 */
public class ClienteControllers {
    
    ClienteDao clienteDao = new ClienteDao();
    
    public void Insert(ClienteBean cliente) throws Exception {

        try {
            clienteDao.inserirRegistro(cliente);
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
}