package br.edu.ifpb.bdnc.projeto.services.impl;

import br.edu.ifpb.bdnc.projeto.dao.impl.CaronaDaoImpl;
import br.edu.ifpb.bdnc.projeto.dao.interfaces.CaronaDAO;
import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import com.vividsolutions.jts.geom.Point;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Edilva
 */
public class CaronaServiceImpl implements CaronaService {

    private CaronaDAO caronaDAO;

    public CaronaServiceImpl() {
        this.caronaDAO = new CaronaDaoImpl();
    }

    @Override
    public boolean salvar(Carona carona) {
        return caronaDAO.salvar(carona);
    }

    @Override
    public boolean remover(int id) {
        return caronaDAO.remover(id);
    }

    @Override
    public boolean editar(Carona carona) {
        return caronaDAO.editar(carona);
    }

    @Override
    public List<Carona> listar() {
        return caronaDAO.listar();
    }

    @Override
    public Carona getCarona(int id) {
        return caronaDAO.getCarona(id);
    }

    @Override
    public List<Carona> caronasDoUsuario(int usuario) {
        return caronaDAO.caronasDoUsuario(usuario);
    }

    @Override
    public List<Carona> caronasPesquisa(Point origem, Point destino, LocalDate data) {
        return caronaDAO.caronasPesquisa(origem, destino, data);
    }

}
