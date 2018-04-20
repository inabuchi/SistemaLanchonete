package br.com.SistemaLanchonete.Validacao;

import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;


public class Validacao {

	/**
     * Método validação de String
     *
     * @param object
     * @return String
     */
    public static String validaString(Object valor) {
        return valor == null ? "" : String.valueOf(valor);
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

        String valorString = obj.toString(); //Como implementação original, se não for numero, tenta-se trabalhar com base no toString. Strings retornam "this" neste método.
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
}