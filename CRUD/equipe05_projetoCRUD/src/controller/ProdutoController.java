package controller;

import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoController {

    public void adicionarProduto(String nome, double preco, String descricao, int quantidadeEstoque) {
        String sql = "INSERT INTO tbProduto (pro_nome, pro_preco, pro_descricao, pro_quantidade_estoque) VALUES (?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setString(3, descricao);
            stmt.setInt(4, quantidadeEstoque);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!\nID: " + idGerado, "Questão", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + e.getMessage(), "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarProduto(int id, String nome, double preco, String descricao, int quantidadeEstoque) {
        String sql = "UPDATE tbProduto SET pro_nome = ?, pro_preco = ?, pro_descricao = ?, pro_quantidade_estoque = ? WHERE pro_id = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setString(3, descricao);
            stmt.setInt(4, quantidadeEstoque);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!", "Questão 01", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar produto!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Produto pesquisarProduto(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM tbProduto WHERE pro_id = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("pro_id"));
                produto.setNome(rs.getString("pro_nome"));
                produto.setPreco(rs.getDouble("pro_preco"));
                produto.setDescricao(rs.getString("pro_descricao"));
                produto.setQtdeEstoque(rs.getInt("pro_quantidade_estoque"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar produto!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }

        return produto;
    }



    public void excluirProduto(int id) {
            Produto produto = pesquisarProduto(id);

        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Questão 01", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "DELETE FROM tbProduto WHERE pro_id = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!", "Questão 01", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregarProdutosNaTabela(DefaultTableModel modelo) {
        String sql = "SELECT * FROM tbProduto";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            modelo.setRowCount(0);

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("pro_id"),
                    rs.getString("pro_nome"),
                    rs.getDouble("pro_preco"),
                    rs.getString("pro_descricao"),
                    rs.getInt("pro_quantidade_estoque")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void carregarProdutosComboBox(JComboBox<String> comboBox) {
        String sql = "SELECT pro_nome FROM tbProduto";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            comboBox.removeAllItems();

            while (rs.next()) {
                comboBox.addItem(rs.getString("pro_nome"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public int buscarIdPorNome(String nome) {
        String sql = "SELECT pro_id FROM tbProduto WHERE pro_nome = ?";
        int id = -1;

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("pro_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ID do produto!", "Questão 01", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
}
