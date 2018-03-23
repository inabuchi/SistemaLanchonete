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
    private int codProdutoAdicional;
    private int codProdutoCategoria;
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
    ProdutoBean(int codProduto, int codProdutoAdicional, int codProdutoCategoria, String referenciaProduto, 
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
     * @return código do produto 
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
     * @return código do produto adicional desejado 
     */
    public int getCodProdutoAdicional() {
        return codProdutoAdicional;
    }

    /**
     * 
     * @param codProdutoAdicional 
     */
    public void setCodProdutoAdicional(int codProdutoAdicional) {
        this.codProdutoAdicional = codProdutoAdicional;
    }
    
    /**
     * 
     * @return Categoria do objeto ProdutoCategoria 
     */
    public int getCodProdutoCategoria() {
        return codProdutoCategoria;
    }

    /**
     *
     * @param codProdutoCategoria 
     */
    public void setCodProdutoCategoria(int codProdutoCategoria) {
        this.codProdutoCategoria = codProdutoCategoria;
    }

    /**
     * 
     * @return Referencia do produto
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
     * @return descrição do produto 
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
     * @return valor total da venda
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
     * @return produto ativo ou não
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
