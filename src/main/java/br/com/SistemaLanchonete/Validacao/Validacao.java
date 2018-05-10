package br.com.SistemaLanchonete.Validacao;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author robertozbnu and Lino Pegoretti
 *
 */
public class Validacao {

	/*
	 * 
	 * M�todos para valida��o de String
	 * 
	 */

	/**
	 * Converte um objeto para String vazia ou valor do objeto
	 *
	 * @param valor
	 *            - um objeto gen�rico para ser convertido
	 * @return String - O valor do objeto em String quando o objeto tiver
	 *         conteudo<br>
	 *         String vazia quando for nulo
	 */
	public static String validaString(Object valor) {
		return valor == null ? "" : String.valueOf(valor);
	}

	/**
	 * Retorna se uma string � nula ou n�o
	 * 
	 * @param stringValidacao
	 *            - uma String para ser validada
	 * @return true - quando for nula<br>
	 *         false - quando vazia ou tem conte�do
	 */
	public static boolean testaStringNula(String stringValidacao) {
		return stringValidacao == null ? true : false;
	}

	/**
	 * Retorna se uma string � vazia ou nao
	 * 
	 * @param stringValidacao
	 *            - uma String para ser validada
	 * @return true - quando for vazia<br>
	 *         false - quando tiver conte�do
	 */
	public static boolean testaStringVazia(String stringValidacao) {
		return testaStringNula(stringValidacao) ? 
				!testaStringNula(stringValidacao)
				: stringValidacao.trim().equals("") ? true : false;
	}

	public static void main(String[] args) {
		String[] valida = { null, " ", "", "      teste", "teste      ", "teste", "123456" };
		System.out.println("validaString");
		for (int i = 0; i < valida.length; i++) {
			System.out.println(i + " --> " + valida[i] + " --> " + validaString(valida[i]));
		}
		System.out.println("testaStringNula = ");
		for (int i = 0; i < valida.length; i++) {
			System.out.println(i + " --> " + valida[i] + " --> " + testaStringNula(valida[i]));
		}
		System.out.println("testaStringVazia = ");
		for (int i = 0; i < valida.length; i++) {
			System.out.println(i + " --> " + valida[i] + " --> " + testaStringVazia(valida[i]));
		}

	}

	/*
	 * 
	 * Métodos para validações de objetos
	 * 
	 */

	/**
	 * Valida um objeto nulo, pode ser qualquer objeto
	 * 
	 * @param objetoValidacao
	 *            - Objeto para ser validado
	 * @return false - se objeto estiver nulo<br>
	 *         true - se nao for nulo
	 */
	public static boolean validaNulo(Object objetoValidacao) {
		return objetoValidacao == null;
	}

	/**
	 * Valida um objeto vazio
	 * 
	 * @param um
	 *            objeto para ser validado
	 * @return false - se objeto estï¿½ vazio true se nao for vazio
	 */
	public static boolean validaVazio(Object objetoValidacao) {
		if (objetoValidacao.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Método validação de Long
	 *
	 * @param object
	 * @return Long
	 */
	public static Long validaLong(Object valor) {
		return valor == null || "".equals(valor.toString()) ? 0L : Long.valueOf(valor.toString());
	}

	/**
	 * Método validação de Integer
	 *
	 * @param object
	 * @return Integer
	 */
	public static Integer validaInteger(Object valor) {
		return valor == null || "".equals(valor.toString()) ? 0 : Integer.valueOf(valor.toString());
	}

	/**
	 * Método validação de Float
	 *
	 * @param object
	 * @return Float
	 */
	public static Float validaFloat(Object obj) {
		return (obj == null || "".equals(obj.toString()) ? 0 : Float.valueOf(obj.toString()));
	}

	/**
	 * Método validação de Double
	 *
	 * @param object
	 * @return Double
	 */
	public static Double validaDouble(Object obj) {
		if (obj instanceof Number) {
			return ((Number) obj).doubleValue();
		}

		if (obj == null || "".equals(obj)) {
			return 0d;
		}

		String valorString = obj.toString(); // Como implementação original, se não for numero, tenta-se trabalhar
												// com
												// base no toString. Strings retornam "this" neste método.
		try {
			return Double.valueOf(valorString);
		} catch (NumberFormatException e) {
			DecimalFormatSymbols dfs = new DecimalFormatSymbols();
			if (dfs.getDecimalSeparator() == ',') {
				if ((obj instanceof String) && (valorString.contains(","))) {
					valorString = valorString.replace(",", ".");
					return Double.valueOf(valorString);
				}
			}
			throw e;
		}
	}

	/**
	 * Método de remoção de caracteres especiais
	 *
	 * @param String
	 * @return String
	 */
	public static String removerCaracteresEspeciais(String texto) {
		return texto.replaceAll("[^aA-zZ-Z1-9 ]", "");
	}

	/**
	 * Método para obter a hora inicial da data passada como parâmetro
	 *
	 * @param date
	 * @return date
	 */
	public static Date getInicioDia(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * Método para obter a hora final da data passada como parâmetro
	 *
	 * @param date
	 * @return date
	 */
	public static Date getFimDia(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * Método para obter o formato de data conforme abaixo 1 = dd/MM/yyyy HH:mm:ss
	 * 2 = dd/MM/yyyy 3 = HH:mm:ss 4 = dd 5 = MM 6 = yyyy 7 = HH 8 = mm 9 = ss 10 =
	 * HH:mm
	 * 
	 * @param int,
	 *            date
	 * @return date
	 * @throws Exception
	 */
	public static Date formatarData(int formato, Date data) throws Exception {
		try {
			SimpleDateFormat formatarDate = null;
			if (formato == 1) {
				formatarDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			} else if (formato == 2) {
				formatarDate = new SimpleDateFormat("dd/MM/yyyy");
			} else if (formato == 3) {
				formatarDate = new SimpleDateFormat("HH:mm:ss");
			} else if (formato == 4) {
				formatarDate = new SimpleDateFormat("dd");
			} else if (formato == 5) {
				formatarDate = new SimpleDateFormat("MM");
			} else if (formato == 6) {
				formatarDate = new SimpleDateFormat("yyyy");
			} else if (formato == 7) {
				formatarDate = new SimpleDateFormat("HH");
			} else if (formato == 8) {
				formatarDate = new SimpleDateFormat("mm");
			} else if (formato == 9) {
				formatarDate = new SimpleDateFormat("ss");
			} else if (formato == 10) {
				formatarDate = new SimpleDateFormat("HH:mm");
			}
			Date date = formatarDate.parse(validaString(data));
			return date;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Método para conversão de String para Double
	 * 
	 * @param stringDouble
	 * @return
	 * @throws SistemaException
	 */
	public static double stringToDouble(String stringDouble) throws SistemaException {
		double doubleRetorno = 0;
		if (testaStringNula(stringDouble) && testaStringVazia(stringDouble)) {
			try {
				doubleRetorno = Double.parseDouble(stringDouble);
			} catch (Exception e) {
				throw new SistemaException(e.getMessage(), EErrosSistema.VALOR_DECIMAL_INVALIDO);
			}
		}
		return doubleRetorno;
	}

	/**
	 * Método para conversão de String para Date
	 * 
	 * @param dataString
	 * @return
	 * @throws SistemaException
	 * @throws ParseException
	 */
	public static Date stringToDate(String dataString) throws SistemaException {
		Date dataRetorno = null;
		if (testaStringNula(dataString) && testaStringVazia(dataString)) {
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

	/**
	 * Método para converter data formatada para String
	 * 
	 * @param dataRetorno
	 * @return
	 */
	public static String getDataFormatada(Date dataRetorno) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(dataRetorno);
	}

	/**
	 * Método para converter String para ID de Classe
	 * 
	 * @param codigoString
	 * @return
	 * @throws SistemaException
	 */
	public static int stringToCodigo(String codigoString) throws SistemaException {
		int codigoRetorno = 0;
		if (testaStringNula(codigoString) && testaStringVazia(codigoString)) {
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
	 * Método para converter String para int
	 * 
	 * @param intString
	 * @return
	 * @throws SistemaException
	 */
	public static int stringToInt(String intString) throws SistemaException {
		int intRetorno = 0;
		if (testaStringNula(intString) && testaStringVazia(intString)) {
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
	 * Método para converter String para boolean
	 * 
	 * @param parameter
	 * @throws SistemaException
	 */

	public static boolean stringToBoolean(String booleanString) throws SistemaException {
		boolean booleanRetorno = false;
		if (testaStringNula(booleanString) && testaStringVazia(booleanString)) {
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
