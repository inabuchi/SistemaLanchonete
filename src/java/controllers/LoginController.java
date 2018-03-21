/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.LoginBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.LoginDao;

/**
 *
 * @author Douglas
 */
public class LoginController {
    
    LoginDao loginDao = new LoginDao();
    
    public void Insert(LoginBean login) throws Exception {

        try {
            loginDao.Insert(login);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Update(LoginBean login) throws Exception {

        try {
            loginDao.Update(login);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Delete(LoginBean login) throws Exception {

        try {
            loginDao.Delete(login);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<LoginBean> DefaultSelect(LoginBean login) throws Exception {

        try {
            return loginDao.DefaultSelect(login);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void InsertOrUpdate(LoginBean login) throws ExceptionBD {
        if (loginDao.GetAsExists(login)) {
            loginDao.Update(login);
        } else {
            loginDao.Insert(login);
        }
    }
    
    public int NextValue() throws ExceptionBD{
        return loginDao.NextValue();
    }
}
