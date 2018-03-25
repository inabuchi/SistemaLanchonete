/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cliente;

import beans.cliente.CargoBean;
import java.util.List;
import models.conexao.ExceptionBD;
import models.dao.cliente.CargoDao;

/**
 *
 * @author Douglas
 */
public class CargoController {
    CargoDao cargoDao = new CargoDao();
    
    public void Insert(CargoBean cargo) throws Exception {

        try {
            cargoDao.Insert(cargo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Update(CargoBean cargo) throws Exception {

        try {
            cargoDao.Update(cargo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void Delete(CargoBean cargo) throws Exception {

        try {
            cargoDao.Delete(cargo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<CargoBean> DefaultSelect(CargoBean cargo) throws Exception {

        try {
            return cargoDao.DefaultSelect(cargo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
