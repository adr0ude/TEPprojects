package Classes;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class RegistroHoras {
    private Funcionario funcionario;
    private Date data;
    private Date entrada;
    private Date saidaIntervalo;
    private Date retornoIntervalo;
    private Date saidaFinal;
    
    public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");

    public RegistroHoras(Funcionario funcionario, Date data, Date entrada, Date saidaIntervalo, Date retornoIntervalo, Date saidaFinal) {
        this.funcionario = funcionario;
        this.data = data;
        this.entrada = entrada;
        this.saidaIntervalo = saidaIntervalo;
        this.retornoIntervalo = retornoIntervalo;
        this.saidaFinal = saidaFinal;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Date getData() {
        return data;
    }

    public Date getEntrada() {
        return entrada;
    }

    public Date getSaidaIntervalo() {
        return saidaIntervalo;
    }

    public Date getRetornoIntervalo() {
        return retornoIntervalo;
    }

    public Date getSaidaFinal() {
        return saidaFinal;
    }
    
    public double calcularHorasTrabalhadas() {
        long total = (saidaFinal.getTime() - entrada.getTime()) - (retornoIntervalo.getTime() - saidaIntervalo.getTime());
        
        return total / (1000.0 * 60 * 60);
    }
    
    public double calcularIntervalo() {
        return (retornoIntervalo.getTime() - saidaIntervalo.getTime()) / (1000 * 60);
    }
    
    public String getResumo() {
        double horasTrabalhadas = calcularHorasTrabalhadas();
        double intervalo = calcularIntervalo();
        
        if (horasTrabalhadas > 10) {
            JOptionPane.showMessageDialog(null, "Atenção!\nO funcionário " + funcionario.getNome() +
                    " excedeu a jornada máxima permitida de 10 horas.\nTotal de horas trabalhadas: " + horasTrabalhadas + "h",
                    "Questão 02", JOptionPane.WARNING_MESSAGE);
        }

        return String.format("Funcionário: %s\nData: %s\nTotal de horas trabalhadas: %.2f horas\nTempo total de intervalo: %.1f minutos\nCumpriu jornada mínima: %s", 
                funcionario.getNome(), sdfData.format(data), horasTrabalhadas, intervalo, (horasTrabalhadas >= 8 ? "Sim" : "Não"));
    }
    
    @Override
    public String toString() {
        return "Funcionário: " + funcionario.getNome() +
               "\nData: " + sdfData.format(data) +
               "\nEntrada: " + sdf.format(entrada) +
               "\nSaída para Intervalo: " + sdf.format(saidaIntervalo) +
               "\nRetorno do Intervalo: " + sdf.format(retornoIntervalo) +
               "\nSaída Final: " + sdf.format(saidaFinal);
    }

}