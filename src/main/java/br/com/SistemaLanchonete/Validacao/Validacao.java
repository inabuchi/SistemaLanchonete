package br.com.SistemaLanchonete.Validacao;

import java.lang.reflect.Field;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.SistemaLanchonete.Repository.ETipoCampo;

/**
 * @author robertozbnu and Lino Pegoretti
 *
 */
public class Validacao {

	public static final String MSG_ATRIBUTO_VAZIO = "Campo %1s  não pode ser vazio";
	public static final String MSG_ATRIBUTO_NULO = "Campo %1s não pode ser nulo";
	public static final String MSG_OBJETO_NULO = "Objeto %1s não pode ser nulo";
	public static final String MSG_OBJETO_GRAVADO = "Objeto %1s gravado com sucesso";

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
	 * Métodos para manipulação de String
	 * 
	 */

	/**
	 * Converte um objeto para String vazia ou valor do objeto
	 *
	 * @param valor
	 *            - um objeto genérico para ser convertido
	 * @return String - O valor do objeto em String quando o objeto tiver
	 *         conteudo<br>
	 *         String vazia quando for nulo
	 */
	public static String validaString(Object valor) {
		return valor == null ? "" : String.valueOf(valor);
	}

	/**
	 * Retorna se uma string é nula ou não
	 * 
	 * @param stringValidacao
	 *            - uma String para ser validada
	 * @return true - quando for nula<br>
	 *         false - quando vazia ou tem conteúdo
	 */
	public static boolean testaStringNula(String stringValidacao) {
		return stringValidacao == null ? true : false;
	}

	/**
	 * Retorna se uma string é vazia ou nao
	 * 
	 * @param stringValidacao
	 *            - uma String para ser validada
	 * @return true - quando for vazia<br>
	 *         false - quando tiver conteúdo ou for nula
	 */
	public static boolean testaStringVazia(String stringValidacao) {
		return testaStringNula(stringValidacao) ? !testaStringNula(stringValidacao)
				: stringValidacao.trim().equals("") ? true : false;
	}

	/**
	 * Conversão de String para Double
	 * 
	 * @param stringDouble
	 *            - um número Double em formato de String
	 * @return double - um número convertido em double
	 * @throws SistemaException
	 *             - um erro de sistema interno
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
	 * Conversão de String para Java.Util.Date
	 * 
	 * @param dataString
	 *            - uma data formatada como String
	 * @return Date - uma data no formato Date do JAVA
	 * @throws SistemaException
	 *             - um erro de sistema interno
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
	 * Conerter da classe Java.Util.Date para String
	 * 
	 * @param dataRetorno
	 *            - uma data a ser formatada
	 * @return Date(dd/MM/yyyy) - uma data nesse formato
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

	/*
	 * 
	 * Métodos para validações de objetos
	 * 
	 */

	/**
	 * Método de validação de objeto nulo(não instanciado),pode ser qualquer objeto
	 * 
	 * @param objetoValidacao
	 *            - Objeto para ser validado
	 * @return false - se objeto estiver nulo<br>
	 *         true - se nao for nulo
	 */
	public static boolean validaNulo(Object objetoValidacao) {
		if (objetoValidacao == null) {
			throw new IllegalArgumentException(String.format(MSG_OBJETO_NULO, Object.class.getSimpleName()));
		}
		return true;
	}

	/**
	 * Valida um objeto vazio
	 * 
	 * @param objetoValidacao
	 *            - objeto para ser validado
	 * @return true - se objeto nao for vazio <br>
	 *         false - se objeto estiver vazio
	 */
	public static boolean validaVazio(Object objetoValidacao) {
		return objetoValidacao.equals("") ? true : false;
	}

	/**
	 * 
	 * Método de validação de atributo nulo
	 * 
	 * @param objeto
	 *            - um objeto passado por parametro
	 * @param field
	 *            - um campo do objeto passado por parametro
	 * @return true se o atributo nao for nulo exceção se for nulo
	 * @throws Exception
	 */
	public static boolean validaAtributoNulo(Object objeto, Field field) throws Exception {
		field.setAccessible(true);
		if (field.get(objeto) == null) {
			throw new Exception(String.format(MSG_ATRIBUTO_NULO, field.getName()));
		}
		field.setAccessible(false);
		return true;
	}

	/**
	 * 
	 * Método de validação de atributo vazio, zerado, ou em branco
	 * 
	 * @param objeto
	 *            - um objeto passado por parametro
	 * @param field
	 *            - um campo do objeto passado por parametro
	 * @return true se o atributo nao for vazio, zerado, ou em branco, caso
	 *         contrário exceção
	 * @throws Exception
	 */
	public static boolean validaAtributoVazio(Object objeto, Field field) throws Exception {
		ETipoCampo tipo = ETipoCampo.valueOf(field.getType().getSimpleName().toUpperCase());
		switch (tipo) {
		case INT:
			field.setAccessible(true);
			if (field.getInt(objeto) == 0) {
				throw new Exception(String.format(MSG_ATRIBUTO_VAZIO, field.getName()));
			}
			field.setAccessible(false);
			break;
		case STRING:
			field.setAccessible(true);
			if (field.get(objeto).toString().equals("")) {
				throw new Exception(String.format(MSG_ATRIBUTO_VAZIO, field.getName()));
			}
			field.setAccessible(false);
			break;
		case DOUBLE:
			field.setAccessible(true);
			if (field.getDouble(objeto) == 0) {
				throw new Exception(String.format(MSG_ATRIBUTO_VAZIO, field.getName()));
			}
			field.setAccessible(false);
			break;
		default:
			break;
		}
		return true;
	}

	/*
	 * Métodos para conversões numericas de objetos
	 */

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
												// com base no toString. Strings retornam "this" neste método.
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
	 * Método para obter o formato de data conforme abaixo<br>
	 * <br>
	 * 1 = dd/MM/yyyy HH:mm:ss <br>
	 * 2 = dd/MM/yyyy <br>
	 * 3 = HH:mm:ss <br>
	 * 4 = dd <br>
	 * 5 = MM <br>
	 * 6 = yyyy <br>
	 * 7 = HH <br>
	 * 8 = mm <br>
	 * 9 = ss <br>
	 * 10 = HH:mm
	 * 
	 * @param formato
	 *            - um inteiro como a lista acima
	 * @param data
	 *            - uma data para ser formatada
	 * @return uma data formatada
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

			Date date = formatarDate.parse(formatarDate.format(data));
			return date;
		} catch (Exception e) {
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
}