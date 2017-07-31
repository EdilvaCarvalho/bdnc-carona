package br.edu.ifpb.bdnc.projeto.services.impl;

import br.edu.ifpb.bdnc.projeto.dao.impl.UsuarioDaoImpl;
import br.edu.ifpb.bdnc.projeto.dao.interfaces.UsuarioDAO;
import br.edu.ifpb.bdnc.projeto.entidades.Usuario;
import br.edu.ifpb.bdnc.projeto.services.interfaces.UsuarioService;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDaoImpl();
    }

    @Override
    public boolean salvar(Usuario usuario) {
        return usuarioDAO.salvar(usuario);
    }

    @Override
    public boolean remover(Usuario usuario) {
        return usuarioDAO.remover(usuario);
    }

    @Override
    public boolean editar(Usuario usuario) {
        return usuarioDAO.editar(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    @Override
    public Usuario getUsuario(int id) {
        return usuarioDAO.getUsuario(id);
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }

}
