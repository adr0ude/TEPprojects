package Classes;

public class Funcionario {
    private String nome;
    private String cpf;
    private String cargo;
    
    public Funcionario(String nome, String cpf, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }
    
    public String getNome() { 
        return nome; 
    }
    
    public String getCpf() { 
        return cpf; 
    }
    
    public String getCargo() { 
        return cargo; 
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Cargo: " + cargo;
    }
}
