
package br.edu.ifpb.bdnc.projeto.services.interfaces;

import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import com.vividsolutions.jts.geom.Point;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Edilva
 */
public interface CaronaService {
    
    boolean salvar(Carona carona);
    boolean remover(int id);
    boolean editar(Carona carona);
    List<Carona> listar();
    Carona getCarona(int id);
    List<Carona> caronasDoUsuario(int usuario);
    List<Carona> caronasPesquisa(Point origem, Point destino, LocalDate data);
}
