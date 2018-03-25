/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao.cliente;

import beans.cliente.FuncionarioBean;
import java.util.ArrayList;
import java.util.List;
import models.conexao.ExceptionBD;

/**
 *
 * @author Douglas
 */
public class FuncionarioDao {
    public boolean Insert(FuncionarioBean funcionario) throws ExceptionBD{
        return true;
    }
    
    public boolean Update(FuncionarioBean funcionario) throws ExceptionBD{
        return true;
    }
    
    public boolean Delete(FuncionarioBean funcionario) throws ExceptionBD{
        return true;
    }
    
    public List<FuncionarioBean> DefaultSelect(FuncionarioBean funcionario) throws ExceptionBD {
        List<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
        return funcionarios;
    }
}
