/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import beans.cliente.FuncionarioBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.cliente.FuncionarioDao;

/**
 *
 * @author Douglas
 */
public class FuncionarioController {
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    
    public void Insert(FuncionarioBean funcionario) throws Exception {

        try {
            funcionarioDao.Insert(funcionario);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Update(FuncionarioBean funcionario) throws Exception {

        try {
            funcionarioDao.Update(funcionario);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Delete(FuncionarioBean funcionario) throws Exception {

        try {
            funcionarioDao.Delete(funcionario);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
