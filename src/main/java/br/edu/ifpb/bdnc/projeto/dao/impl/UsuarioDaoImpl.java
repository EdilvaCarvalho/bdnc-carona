package br.edu.ifpb.bdnc.projeto.dao.impl;

import br.edu.ifpb.bdnc.projeto.dao.interfaces.UsuarioDAO;
import br.edu.ifpb.bdnc.projeto.entidades.Sexo;
import br.edu.ifpb.bdnc.projeto.entidades.Usuario;
import br.edu.ifpb.bdnc.projeto.services.impl.CaronaServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import br.edu.ifpb.bdnc.projeto.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edilva
 */
public class UsuarioDaoImpl implements UsuarioDAO {

    private Connection connection;

    public UsuarioDaoImpl() {
    }

    @Override
    public boolean salvar(Usuario usuario) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO usuario (nome, email, senha, telefone, "
                    + "data_nasc, sexo) "
                    + "VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.setDate(5, Date.valueOf(usuario.getDataNasc()));
            ps.setString(6, usuario.getSexo().name());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean remover(Usuario usuario) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean editar(Usuario usuario) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE usuario SET nome = ?, email = ?, "
                    + "senha = ?, telefone = ?, data_nasc = ?, sexo = ?)");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getTelefone());
            ps.setDate(5, Date.valueOf(usuario.getDataNasc()));
            ps.setString(6, usuario.getSexo().name());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usuarios.add(dadosDoUsuario(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuario(int id) {
        Usuario usuario = null;
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario = dadosDoUsuario(rs);
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = null;
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario "
                    + "WHERE email = ? AND senha = ?");
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario = dadosDoUsuario(rs);
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    private Usuario dadosDoUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        usuario.setSexo(Sexo.valueOf(rs.getString("sexo")));
        return usuario;
    }

}
