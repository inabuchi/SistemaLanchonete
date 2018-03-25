/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import beans.cliente.PessoaBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.cliente.PessoaDao;

/**
 *
 * @author Douglas
 */
public class PessoaController {
    PessoaDao pessoaDao = new PessoaDao();
    
    public void Insert(PessoaBean pessoa) throws Exception {

        try {
            pessoaDao.Insert(pessoa);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Update(PessoaBean pessoa) throws Exception {

        try {
            pessoaDao.Update(pessoa);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Delete(PessoaBean pessoa) throws Exception {

        try {
            pessoaDao.Delete(pessoa);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<PessoaBean> DefaultSelect(PessoaBean pessoa) throws Exception {

        try {
            return pessoaDao.DefaultSelect(pessoa);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
