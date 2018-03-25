/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao.cliente;

import beans.cliente.CargoBean;
import java.util.ArrayList;
import java.util.List;
import models.conexao.ExceptionBD;

/**
 *
 * @author Douglas
 */
public class CargoDao {
    public boolean Insert(CargoBean cargo) throws ExceptionBD{
        return true;
    }
    
    public boolean Update(CargoBean cargo) throws ExceptionBD{
        return true;
    }
    
    public boolean Delete(CargoBean cargo) throws ExceptionBD{
        return true;
    }
    
    public List<CargoBean> DefaultSelect(CargoBean cargo) throws ExceptionBD {
        List<CargoBean> cargos = new ArrayList<CargoBean>();
        return cargos;
    }
}
