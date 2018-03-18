/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Douglas
 */
public class Conexao {

    private static Connection conexao = null;

    public static void fechaConexao() throws ExceptionBD {
        try {
            if (conexao != null) {
                conexao.close();
            }
            conexao = null;
        } catch (Exception e) {
            throw new ExceptionBD(e.getMessage(), EErrosBD.FECHA_CONEXAO);
        }
    }

    public static Connection getConexao() throws ExceptionBD {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://sistemalanchonetebd.c3vx8k5m6fma.sa-east-1.rds.amazonaws.com:3306/SistemaLanchoneteBD?useSSL=true", "usuarioAdmin", "usuario123");
            return conexao;
        } catch (Exception e) {

            throw new ExceptionBD(e.getMessage(), EErrosBD.ABRE_CONEXAO);
        }
    }
}
