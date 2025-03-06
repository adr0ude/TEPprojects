package Classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class ControleHorarios {
    private static List<RegistroHoras> registros = new ArrayList<>();
    private static List<Funcionario> funcionarios = new ArrayList<>();
    
    
    public static void cadastrarFuncionario(String nome, String cpf, String cargo) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                JOptionPane.showMessageDialog(null, "Funcionário com este CPF já está cadastrado! \nTente novamente.", "Questão 02", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        Funcionario funcionario = new Funcionario(nome, cpf, cargo);
        funcionarios.add(funcionario);
        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Questão 02", JOptionPane.PLAIN_MESSAGE);
    }

    
    public static void registrarHorarios(Funcionario funcionario, Date data, Date entrada, Date saidaIntervalo, Date retornoIntervalo, Date saidaFinal) {
        for (RegistroHoras registro : registros) {
            if (registro.getFuncionario().equals(funcionario) && registro.getData().equals(data)) {
                JOptionPane.showMessageDialog(null, "Funcionário já possui registro para esta data!\nTente novamente.", "Questão 02", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        RegistroHoras registro = new RegistroHoras(funcionario, data, entrada, saidaIntervalo, retornoIntervalo, saidaFinal);
        JOptionPane.showMessageDialog(null, "Horário registrado com sucesso!", "Questão 02", JOptionPane.PLAIN_MESSAGE);
        registros.add(registro);
        JOptionPane.showMessageDialog(null, exibirResumo(funcionario) , "Questão 02", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static List<Funcionario> exibirFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public static List<RegistroHoras> exibirRegistros() {
        return new ArrayList<>(registros);
    }
    
    public static String exibirResumo(Funcionario funcionario) {
        for (RegistroHoras registro : registros) {
            if (registro.getFuncionario().equals(funcionario)) {
                return registro.getResumo();
            }
        }
        return "Nenhum registro encontrado para esse funcionário.";
    }
    
    public static Funcionario buscarFuncionario(String nomeFuncionario) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nomeFuncionario)) {
                return funcionario;
            }
        }
        return null;
    }
    
    public static void buscarRegistro() { 
        for (Funcionario funcionario : funcionarios) {
            boolean temRegistro = false;
            for (RegistroHoras registro : registros) {
                if (registro.getFuncionario().equals(funcionario)) {
                    temRegistro = true;
                    break;
                }
            }

            if (!temRegistro) {
                JOptionPane.showMessageDialog(null, "Funcionário " + funcionario.getNome() + " não tem registros!", "Questão 02", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public static void exportarParaCSV() {
        
        if (registros == null || registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum horário registrado!", "Questão 02", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        buscarRegistro();
        
        String arquivoCSV = "Registros_Diarios.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCSV))) {
            writer.write("Funcionário,Data,Entrada,Saída para Intervalo,Retorno do Intervalo,Saída Final,Horas Trabalhadas\n");

            for (RegistroHoras registro : registros) {
                writer.write(registro.getFuncionario().getNome() + ",");
                writer.write(RegistroHoras.sdfData.format(registro.getData()) + ",");
                writer.write(RegistroHoras.sdf.format(registro.getEntrada()) + ",");
                writer.write(RegistroHoras.sdf.format(registro.getSaidaIntervalo()) + ",");
                writer.write(RegistroHoras.sdf.format(registro.getRetornoIntervalo()) + ",");
                writer.write(RegistroHoras.sdf.format(registro.getSaidaFinal()) + ",");
                writer.write(String.format("%.2f", registro.calcularHorasTrabalhadas()).replace(',', '.'));
            }

            JOptionPane.showMessageDialog(null, "Dados exportados para " + arquivoCSV, "Exportação Concluída", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao exportar para CSV: " + e.getMessage(), "Erro na Exportação", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void exportarParaTXT() {
        
        if (registros == null || registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum horário registrado!", "Questão 02", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        buscarRegistro();
        
        String arquivoTXT = "Registros_Diarios.txt";
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTXT))) {
            for (RegistroHoras registro : registros) {
                writer.write("Funcionário: " + registro.getFuncionario().getNome() + "\n");
                writer.write("Data: " + RegistroHoras.sdfData.format(registro.getData()) + "\n");
                writer.write("Entrada: " + RegistroHoras.sdf.format(registro.getEntrada()) + "\n");
                writer.write("Saída para Intervalo: " + RegistroHoras.sdf.format(registro.getSaidaIntervalo()) + "\n");
                writer.write("Retorno do Intervalo: " + RegistroHoras.sdf.format(registro.getRetornoIntervalo()) + "\n");
                writer.write("Saída Final: " + RegistroHoras.sdf.format(registro.getSaidaFinal()) + "\n");
                writer.write(String.format("Horas Trabalhadas: %.2f\n", registro.calcularHorasTrabalhadas()));
                writer.write("---------------------------------------\n");
            }

            JOptionPane.showMessageDialog(null, "Dados exportados para " + arquivoTXT, "Questão 02", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao exportar para TXT: " + e.getMessage(), "Questão 02", JOptionPane.ERROR_MESSAGE);
        }
    }
       
}
