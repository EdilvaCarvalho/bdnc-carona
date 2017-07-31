
package br.edu.ifpb.bdnc.projeto.dao.interfaces;

import br.edu.ifpb.bdnc.projeto.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface UsuarioDAO {
    
    boolean salvar(Usuario usuario);
    boolean remover(Usuario usuario);
    boolean editar(Usuario usuario);
    List<Usuario> listar();
    Usuario getUsuario(int id);
    Usuario autenticar(String email, String senha);
}
