/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao.cliente;

import beans.cliente.PessoaBean;
import java.util.ArrayList;
import java.util.List;
import models.conexao.ExceptionBD;

/**
 *
 * @author Douglas
 */
public class PessoaDao {
    public boolean Insert(PessoaBean pessoa) throws ExceptionBD{
        return true;
    }
    
    public boolean Update(PessoaBean pessoa) throws ExceptionBD{
        return true;
    }
    
    public boolean Delete(PessoaBean pessoa) throws ExceptionBD{
        return true;
    }
    
    public List<PessoaBean> DefaultSelect(PessoaBean pessoa) throws ExceptionBD {
        List<PessoaBean> pessoas = new ArrayList<PessoaBean>();
        return pessoas;
    }
}
