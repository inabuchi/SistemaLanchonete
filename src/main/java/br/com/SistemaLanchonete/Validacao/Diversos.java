/**
 * 
 */
package br.com.SistemaLanchonete.Validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lino Pegoretti
 *
 */
public class Diversos {

	/**
	 * @param ordenacao
	 * @return
	 */
	public static String validaOrdenacao(String ordenacao) {
		ordenacao = ordenacao instanceof String
				? (ordenacao
						.equalsIgnoreCase(Constantes.ORDEM_ASCENDENTE)
								? Constantes.ORDEM_ASCENDENTE
								: (ordenacao.equalsIgnoreCase(Constantes.ORDEM_DESCENDENTE)
										? Constantes.ORDEM_DESCENDENTE : Constantes.ORDEM_ASCENDENTE))
				: Constantes.ORDEM_ASCENDENTE;
		return ordenacao;
	}

	/**
	 * @param campoOrdenacaoString
	 * @return
	 */
	public static int campoOrdenacao(String campoOrdenacaoString) {
		int campoOrdenacao = 0;
		try {
			campoOrdenacao = Integer.parseInt(campoOrdenacaoString);
		} catch (Exception e) {
			campoOrdenacao = 0;
		}
		return campoOrdenacao;
	}

	/**
	 * @param stringValidacao
	 * @return
	 */

	public static boolean testaStringNaoNula(String stringValidacao) {

		return stringValidacao != null;

	}

	public static boolean testaStringNaoVazia(String stringValidacao) {
		return !stringValidacao.trim().equals("");
	}

	/**
	 * @param stringConversao
	 * @return
	 */
	public static String stringToVazia(String stringConversao) {
		if (stringConversao.equals(null)) {
			stringConversao = "";
		}
		return stringConversao;
	}

	/**
	 * Valida um objeto nulo
	 * 
	 * @param um
	 *            objeto para ser validado
	 * @return false - se objeto est� nulo true se nao for nulo
	 */
	public static boolean validaNulo(Object objetoValidacao) {
		if (objetoValidacao == null) {
			return false;
		}
		return true;
	}

	/**
	 * Valida um objeto vazio
	 * 
	 * @param um
	 *            objeto para ser validado
	 * @return false - se objeto est� vazio true se nao for vazio
	 */
	public static boolean validaVazio(Object objetoValidacao) {
		if (objetoValidacao.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * @param stringDouble
	 * @return
	 * @throws SistemaException
	 */
	public static double stringToDouble(String stringDouble) throws SistemaException {
		double doubleRetorno = 0;
		if (testaStringNaoNula(stringDouble) && testaStringNaoVazia(stringDouble)) {
			try {
				doubleRetorno = Double.parseDouble(stringDouble);
			} catch (Exception e) {
				throw new SistemaException(e.getMessage(), EErrosSistema.VALOR_DECIMAL_INVALIDO);
			}
		}
		return doubleRetorno;
	}

	/**
	 * @param dataString
	 * @return
	 * @throws SistemaException
	 * @throws ParseException
	 */
	public static Date stringToDate(String dataString) throws SistemaException {
		Date dataRetorno = null;
		if (testaStringNaoNula(dataString) && testaStringNaoVazia(dataString)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dataRetorno = new Date(sdf.parse(dataString).getTime());
			} catch (Exception e) {
				throw new SistemaException(e.getMessage(), EErrosSistema.DATA_INVALIDA);
			}
		} else {
			dataRetorno = new Date();
		}
		return dataRetorno;
	}

	public static String getDataFormatada(Date dataRetorno) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(dataRetorno);
	}

	/**
	 * @param codigoString
	 * @return
	 * @throws SistemaException
	 */
	public static int stringToCodigo(String codigoString) throws SistemaException {
		int codigoRetorno = 0;
		if (testaStringNaoNula(codigoString) && testaStringNaoVazia(codigoString)) {
			try {
				codigoRetorno = Integer.parseInt(codigoString);
			} catch (Exception e) {
				throw new SistemaException(e.getMessage(), EErrosSistema.CODIGO_INVALIDO);
			}
		} else {
			throw new SistemaException("Erro de Sistema - ", EErrosSistema.CODIGO_NULO);
		}
		return codigoRetorno;
	}

	/**
	 * @param intString
	 * @return
	 * @throws SistemaException
	 */
	public static int intToString(String intString) throws SistemaException {
		int intRetorno = 0;
		if (testaStringNaoNula(intString) && testaStringNaoVazia(intString)) {
			try {
				intRetorno = Integer.parseInt(intString);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SistemaException(e.getMessage(), EErrosSistema.VALOR_DECIMAL_INVALIDO);
			}
		}
		return intRetorno;
	}

	/**
	 * @param parameter
	 * @throws SistemaException
	 */

	public static boolean stringToBoolean(String booleanString) throws SistemaException {
		boolean booleanRetorno = false;
		if (testaStringNaoNula(booleanString) && testaStringNaoVazia(booleanString)) {
			try {
				booleanRetorno = Boolean.parseBoolean(booleanString);

			} catch (Exception e) {
				e.printStackTrace();
				throw new SistemaException(e.getMessage(), EErrosSistema.OPCAO_INVALIDA);
			}
		}
		return booleanRetorno;
	}
}
