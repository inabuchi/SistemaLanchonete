/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.cliente;

/**
 *
 * @author Douglas
 */
public class CargoBean {
    private int cdCargo;
    private String dsCargo;

    public CargoBean() {
        this(0,"");
    }

    public CargoBean(int cdCargo, String dsCargo) {
        setCdCargo(cdCargo);
        setDsCargo(dsCargo);
    }

    public int getCdCargo() {
        return cdCargo;
    }

    public void setCdCargo(int cdCargo) {
        this.cdCargo = cdCargo;
    }

    public String getDsCargo() {
        return dsCargo;
    }

    public void setDsCargo(String dsCargo) {
        this.dsCargo = dsCargo;
    }
}
