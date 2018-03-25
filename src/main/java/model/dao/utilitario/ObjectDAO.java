/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.utilitario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.conexao.Conexao;
import models.conexao.EErrosBD;
import models.conexao.ExceptionBD;
import model.dao.consts.ConstantesBD;

/**
 *
 * @author Patrick
 */
public class ObjectDAO {
    
	private String tabela;

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
        
	public ArrayList<String> getCamposTabela() throws ExceptionBD {
		Connection conn = Conexao.getConexao();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT ORDINAL_POSITION AS ORDEM, COLUMN_NAME AS COLUNA, DATA_TYPE AS TIPO, IF(COLUMN_KEY = '', 'NO', COLUMN_KEY) CHAVE "
							+ "FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'SistemaLanchonete' AND TABLE_NAME = ? "
							+ "ORDER BY ORDINAL_POSITION");
			ps.setString(1, getTabela());
			ResultSet rs = ps.executeQuery();

			ArrayList<String> campos = new ArrayList();
			while (rs.next()) {
				campos.add(rs.getString("COLUNA") + "," + rs.getString("TIPO") + "," + rs.getString("CHAVE"));
			}

			return campos;

		} catch (Exception e) {
			throw new ExceptionBD(e.getMessage(), EErrosBD.BUSCAR_CAMPOS_TABELA);
		}
	}

	protected boolean inserirRegistro(List<FieldsAndValues> valores) throws ExceptionBD {
		Connection conn = Conexao.getConexao();
		try {
			String comando = "INSERT INTO " + getTabela() + "(";
			String values = "";

			ArrayList<String> campos = getCamposTabela();
			String campoTipo[] = new String[3];

			for (int i = 0; i < campos.size(); i++) {
				campoTipo = campos.get(i).split(",");
				comando += campoTipo[0];
				comando = (i + 1 < campos.size() ? (comando += ", ") : comando);
				values += "?";
				values = (i + 1 < campos.size() ? (values += ", ") : values);
			}

			comando += ") VALUES(" + values + ");";
			PreparedStatement ps = conn.prepareStatement(comando);

			int i = 1;

			for (String campo : campos) {
				campoTipo = campo.split(",");
				for (FieldsAndValues fv : valores) {
					if (campoTipo[0].equalsIgnoreCase(fv.getField())) {
						switch (campoTipo[1]) {
						case ConstantesBD.INTEIRO:
							if ((campoTipo[2].equalsIgnoreCase(ConstantesBD.FOREIGN_KEY))
									&& (fv.getValue().equalsIgnoreCase("0"))) {
								ps.setNull(i, java.sql.Types.INTEGER);
							} else {
								ps.setInt(i, fv.getValueInt());
							}
							break;
						case ConstantesBD.VARCHAR:                                                     
							ps.setString(i, fv.getValue());
							break;
                                                case ConstantesBD.LONGTEXT:
                                                    ps.setString(i, fv.getValue());
                                                    break;
						case ConstantesBD.DECIMAL:
							ps.setDouble(i, fv.getValueDouble());
							break;
						case ConstantesBD.DOUBLE:
							ps.setDouble(i, fv.getValueDouble());
							break;                                                        
						case ConstantesBD.FLOAT:
							ps.setFloat(i, fv.getValueFloat());
							break;                                                                                                                
						case ConstantesBD.DATE:
							ps.setDate(i, new java.sql.Date(fv.getValueDate()));
							break;
						case ConstantesBD.DATETIME:
							ps.setDate(i, new java.sql.Date(fv.getValueDate()));
							break;                                                        
						case ConstantesBD.CHAR:
							ps.setString(i, String.valueOf(fv.getValue().charAt(0)));
							break;
						}
						++i;
						break;
					}
				}
			}

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			throw new ExceptionBD(e.getMessage(), EErrosBD.INSERE_DADO);
		} finally {
			Conexao.fechaConexao();
		}
	}

	protected boolean alterarRegistro(List<FieldsAndValues> valores) throws ExceptionBD {
		Connection conn = Conexao.getConexao();
		try {
			String comando = "UPDATE " + getTabela() + " SET ";

			String condicao = "";
			List<String> campos = getCamposTabela();
			String campoTipo[] = new String[3];

			for (FieldsAndValues fv : valores) {
				for (String campo : campos) {
					campoTipo = campo.split(",");
					if (campoTipo[0].equalsIgnoreCase(fv.getField())) {
						if (campoTipo[2].equals(ConstantesBD.PRIMARY_KEY)) {
							condicao += (condicao.toUpperCase().contains("WHERE") ? " AND " : " WHERE ");
							condicao += fv.getField() + " = ?";
							fv.setKey(true);
							continue;
						} else {
							if ((campoTipo[2].equalsIgnoreCase(ConstantesBD.FOREIGN_KEY))
									&& (fv.getValue().equalsIgnoreCase("0"))) {
								continue;
							} else {
								comando += (!fv.getValue().equalsIgnoreCase("") ? fv.getField() + " = ?," : "");
								fv.setKey(false);
								break;
							}
						}
					}
				}
			}
			comando = comando.substring(0, comando.length() - 1) + condicao;

			PreparedStatement ps = conn.prepareStatement(comando);

			int i = 1;
			/* Primeiro os campos que vão ser alterados */
			for (FieldsAndValues fv : valores) {
				if (!fv.isKey()) {
					for (String campo : campos) {
						campoTipo = campo.split(",");
						if (campoTipo[2].equalsIgnoreCase(ConstantesBD.FOREIGN_KEY)
								&& (fv.getValue().equalsIgnoreCase("0"))) {
							continue;
						}

						if ((campoTipo[0].equalsIgnoreCase(fv.getField())) && (!fv.getValue().equalsIgnoreCase(""))) {
							switch (campoTipo[1]) {
							case ConstantesBD.INTEIRO:
								ps.setInt(i, fv.getValueInt());
								break;
							case ConstantesBD.VARCHAR:
								ps.setString(i, fv.getValue());
								break;
							case ConstantesBD.DECIMAL:
								ps.setDouble(i, fv.getValueDouble());
								break;
							case ConstantesBD.DATE:
								ps.setDate(i, new java.sql.Date(fv.getValueDate()));
								break;
							case ConstantesBD.CHAR:
								ps.setString(i, String.valueOf(fv.getValue().charAt(0)));
								break;
							}
							++i;
							break;
						}
					}
				}
			}

			/* Aqui seta os valores da condição */
			for (String campo : campos) {
				campoTipo = campo.split(",");
				for (FieldsAndValues fv : valores) {
					if ((fv.isKey()) && (campoTipo[0].equalsIgnoreCase(fv.getField()))) {
						switch (campoTipo[1]) {
						case ConstantesBD.INTEIRO:
							ps.setInt(i, fv.getValueInt());
							break;
						case ConstantesBD.VARCHAR:
							ps.setString(i, fv.getValue());
							break;
						case ConstantesBD.DECIMAL:
							ps.setDouble(i, fv.getValueDouble());
							break;
						case ConstantesBD.DATE:
							ps.setDate(i, new java.sql.Date(fv.getValueDate()));
							break;
						case ConstantesBD.CHAR:
							ps.setString(i, String.valueOf(fv.getValue().charAt(0)));
							break;
						}
						++i;
						break;
					}
				}
			}

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			throw new ExceptionBD(e.getMessage(), EErrosBD.ATUALIZA_DADO);
		} finally {
			Conexao.fechaConexao();
		}
	}

	protected boolean deletarRegistro(List<FieldsAndValues> valoresChave) throws ExceptionBD {
		Connection conn = Conexao.getConexao();
		try {
			String comando = "DELETE FROM " + getTabela();

			for (int i = 0; i < valoresChave.size(); i++) {
				comando += (comando.toUpperCase().contains("WHERE") ? " AND " : " WHERE ");
				comando += valoresChave.get(i).getField() + " = ? ";
			}

			PreparedStatement ps = conn.prepareStatement(comando);

			List<String> campos = getCamposTabela();
			String campoTipo[] = new String[3];
			int i = 1;
			for (FieldsAndValues fv : valoresChave) {
				for (String c : campos) {
					campoTipo = c.split(",");
					if (campoTipo[0].equalsIgnoreCase(fv.getField())) {
						switch (campoTipo[1]) {
						case ConstantesBD.INTEIRO:
							ps.setInt(i, fv.getValueInt());
							break;
						case ConstantesBD.VARCHAR:
							ps.setString(i, fv.getValue());
							break;
						case ConstantesBD.DECIMAL:
							ps.setDouble(i, fv.getValueDouble());
							break;
						case ConstantesBD.DATE:
							ps.setDate(i, new java.sql.Date(fv.getValueDate()));
							break;
						case ConstantesBD.CHAR:
							ps.setString(i, String.valueOf(fv.getValue().charAt(0)));
							break;
						}
						break;
					}
				}
				++i;
			}
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			throw new ExceptionBD(e.getMessage(), EErrosBD.EXCLUI_DADO);
		} finally {
			Conexao.fechaConexao();
		}
	}        
    
}