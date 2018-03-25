/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.utilitario;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Patrick
 */
public class FieldsAndValues {
	private String field;
	private String value;
	private boolean isKey;
	
	public FieldsAndValues(String field, String value, boolean isKey) {
		setField(field);
		setValue(value);
		setKey(isKey);
	}
	
	public FieldsAndValues(String field, String value){
		this(field, value, false);
	}
        
	public FieldsAndValues(String field, Integer value){
		this(field, Integer.toString(value), false);
	}        
        
	public FieldsAndValues(String field, Integer value, boolean isKey){
		this(field, Integer.toString(value), isKey);
	}        
        
	public FieldsAndValues(String field, float value){
		this(field, Float.toString(value), false);
	}                                        
        
	public FieldsAndValues(String field, boolean value){
		this(field, Boolean.toString(value), false);
	}    
        
	public FieldsAndValues(String field, Date value){
		this(field, new SimpleDateFormat("dd/MM/yyyy").format(value), false);
	}         
	
	public FieldsAndValues() {
		this("", "", false);
	}	
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getValue() {
		return value;
	}
        
        public float getValueFloat() {
            return Float.parseFloat(value);
        }
        
        public double getValueDouble() {
            return Double.parseDouble(value);
        }
        
        public long getValueDate(){
            SimpleDateFormat sdfdt = new SimpleDateFormat("dd/MM/yyyy");
            try {
              return sdfdt.parse(value).getTime();
            } catch(Exception e) {
                return 0;
            }
        }
        
        public int getValueInt() {
            return Integer.parseInt(value);
        }
	
	public void setValue(String value) {
		this.value = value;
	}	
	
	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	@Override
	public String toString() {
		return "\n\tCampo: " + getField() +
			   "\n\tValor: " + getValue() +
			   "\n\tChave: " + (isKey() ? "Sim" : "Não");
	}
}