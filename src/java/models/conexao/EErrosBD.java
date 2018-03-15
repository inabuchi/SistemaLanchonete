/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.conexao;

/**
 *
 * @author Douglas
 */
public enum EErrosBD {
    ABRE_CONEXAO("Erro ao abrir conexão com o banco de dados"),
    FECHA_CONEXAO("Erro ao fechar a conexão com o banco de dados"),
    CRIA_TABELA("Erro ao criar a tabela específica"),
    DROPA_TABELA("Erro ao dropar a tabelar específica"),
    INSERE_DADO("Erro ao inserir dados na tabela específica"),
    ROLLBACK("Erro ao desfazer as alterações(Rollback)"),
    CONSULTA_DADO("Erro ao consultar os dados desejados"),
    ATUALIZA_DADO("Erro ao atualizar os dados"),
    EXCLUI_DADO("Erro ao excluir os dados desejados"),
    TABELA_NAO_CRIADA("Tabela não criada"),
    CRIA_BD("Erro ao criar o banco de dados específico"),
    LISTA_DADO("Erro ao listar a tabela desejada"),
    ATUALIZA_IMAGEM_DADO("Erro ao atualizar imagem"),
    VISUALIZA_DADO("Erro ao visualizar os dados");

    private final String erro;

    public String getErro() {
        return erro;
    }

    private EErrosBD(String erro) {
        this.erro = erro;
    }
}
