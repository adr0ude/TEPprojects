package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String descricao;
    private int qtdeEstoque;
    
    public Produto() {
    }
    
    public Produto(int id, String nome, double preco, String descricao, int qtdeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.qtdeEstoque = qtdeEstoque;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
    
}
