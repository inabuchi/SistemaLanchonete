/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.produto;

/**
 *
 * @author JONATAN JOSÉ SOARES
 */
public class ProdutoBean {
   
    private int codProduto;
    private ProdutoAdicionalBean codProdutoAdicional;
    private ProdutoCategoriaBean codProdutoCategoria;
    private String referenciaProduto;
    private String descProduto;
    private float valVenda;
    private boolean isAtivo;
    
    /**
     * 
     * @param codProduto
     * @param codProdutoAdicional
     * @param codProdutoCategoria
     * @param referenciaProduto
     * @param descProduto
     * @param valVenda
     * @param isAtivo 
     */
    ProdutoBean(int codProduto,  ProdutoAdicionalBean codProdutoAdicional, ProdutoCategoriaBean codProdutoCategoria, String referenciaProduto, 
            String descProduto, float valVenda, boolean isAtivo) {
        this.codProduto = codProduto;
        this.codProdutoAdicional = codProdutoAdicional;
        this.codProdutoCategoria = codProdutoCategoria;
        this.referenciaProduto = referenciaProduto;
        this.descProduto = descProduto;
        this.valVenda = valVenda;
        this.isAtivo = isAtivo;
    }

    /**
     * 
     * @return Código do Produto
     */
    public int getCodProduto() {
        return codProduto;
    }
    
    /**
     * 
     * 
     * @param codProduto 
     */
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
    
    /**
     * 
     * @return Código do Produto Adicional
     */
    public ProdutoAdicionalBean getCodProdutoAdicional() {
        return codProdutoAdicional;
    }

    /**
     * 
     * @param codProdutoAdicional 
     */
    public void setCodProdutoAdicional(ProdutoAdicionalBean codProdutoAdicional) {
        this.codProdutoAdicional = codProdutoAdicional;
    }
    
    /**
     * 
     * @return Categoria do objeto ProdutoCategoria 
     */
    public ProdutoCategoriaBean getCodProdutoCategoria() {
        return codProdutoCategoria;
    }

    /**
     *
     * @param codProdutoCategoria 
     */
    public void setCodProdutoCategoria(ProdutoCategoriaBean codProdutoCategoria) {
        this.codProdutoCategoria = codProdutoCategoria;
    }

    /**
     * 
     * @return Referencia de Produto
     */
    public String getReferenciaProduto() {
        return referenciaProduto;
    }
    /**
     * 
     * @param referenciaProduto 
     */
    public void setReferenciaProduto(String referenciaProduto) {
        this.referenciaProduto = referenciaProduto;
    }
    
    /**
     * 
     * @return Descrição de Produto
     */
    public String getDescProduto() {
        return descProduto;
    }
   /**
    * 
    * @param descProduto 
    */ 

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    /**
     * 
     * @return Valor total de Venda
     */
    public float getValVenda() {
        return valVenda;
    }
    /**
     * 
     * @param valVenda 
     */
    public void setValVenda(float valVenda) {
        this.valVenda = valVenda;
    }

    /**
     * 
     * @return Booleano: Ativo ou Inativo
     */
    public boolean isIsAtivo() {
        return isAtivo;
    }
    
    /**
     * 
     * @param isAtivo 
     */
    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }
}
