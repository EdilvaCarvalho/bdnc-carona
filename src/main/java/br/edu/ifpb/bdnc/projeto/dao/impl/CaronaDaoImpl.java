package br.edu.ifpb.bdnc.projeto.dao.impl;

import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import java.util.List;
import br.edu.ifpb.bdnc.projeto.dao.interfaces.CaronaDAO;
import br.edu.ifpb.bdnc.projeto.services.impl.UsuarioServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.UsuarioService;
import br.edu.ifpb.bdnc.projeto.util.Conexao;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edilva
 */
public class CaronaDaoImpl implements CaronaDAO {

    private Connection connection;

    public CaronaDaoImpl() {
    }

    @Override
    public boolean salvar(Carona carona) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO carona (usuario, origem, destino, "
                    + "data_viagem, hora_saida, duracao, ajuda_custo, distancia, origem_txt, destino_txt) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, carona.getUsuario().getId());
            ps.setString(2, new WKTWriter().write(carona.getOrigem()));
            ps.setString(3, new WKTWriter().write(carona.getDestino()));
            ps.setDate(4, Date.valueOf(carona.getDataViagem()));
            ps.setTime(5, Time.valueOf(carona.getHoraSaida()));
            ps.setString(6, carona.getDuracao());
            ps.setDouble(7, carona.getAjudaCusto());
            ps.setString(8, carona.getDistancia());
            ps.setString(9, carona.getOrigemTxt());
            ps.setString(10, carona.getDestinoTxt());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean remover(int id) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM carona WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean editar(Carona carona) {
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE carona SET origem = ?, destino = ?, "
                    + "data_viagem = ?, hora_saida = ?, duracao = ?, ajuda_custo = ?, distancia = ?, "
                    + "origem_txt = ?, destino_txt = ?");
            ps.setString(1, new WKTWriter().write(carona.getOrigem()));
            ps.setString(2, new WKTWriter().write(carona.getDestino()));
            ps.setDate(3, Date.valueOf(carona.getDataViagem()));
            ps.setTime(4, Time.valueOf(carona.getHoraSaida()));
            ps.setString(5, carona.getDuracao());
            ps.setDouble(6, carona.getAjudaCusto());
            ps.setString(7, carona.getDistancia());
            ps.setString(8, carona.getOrigemTxt());
            ps.setString(9, carona.getDestinoTxt());
            ps.executeUpdate();
            ps.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Carona> listar() {
        List<Carona> caronas = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carona");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caronas.add(dadosDaCarona(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caronas;
    }

    @Override
    public Carona getCarona(int id) {
        Carona carona = null;
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carona WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                carona = dadosDaCarona(rs);
            }
            ps.close();
            connection.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carona;
    }

    private Carona dadosDaCarona(ResultSet rs) throws SQLException, ParseException {
        Carona carona = new Carona();
        carona.setId(rs.getInt("id"));
        Point origem = (Point) new WKTReader().read(rs.getString("origem"));
        carona.setOrigem(origem);
        Point destino = (Point) new WKTReader().read(rs.getString("destino"));
        carona.setDestino(destino);
        carona.setDataViagem(rs.getDate("data_viagem").toLocalDate());
        carona.setHoraSaida(rs.getTime("hora_saida").toLocalTime());
        carona.setDuracao(rs.getString("duracao"));
        carona.setAjudaCusto(rs.getDouble("ajuda_custo"));
        carona.setDistancia(rs.getString("distancia"));
        carona.setOrigemTxt(rs.getString("origem_txt"));
        carona.setDestinoTxt(rs.getString("destino_txt"));
        UsuarioService service = new UsuarioServiceImpl();
        carona.setUsuario(service.getUsuario(rs.getInt("usuario")));
        return carona;
    }

    @Override
    public List<Carona> caronasDoUsuario(int usuario) {
        List<Carona> caronas = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carona WHERE usuario = ?");
            ps.setInt(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caronas.add(dadosDaCarona(rs));
            }
            ps.close();
            connection.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caronas;
    }

    @Override
    public List<Carona> caronasPesquisa(Point origem, Point destino, LocalDate data) {
        List<Carona> caronas = new ArrayList<>();
        List<Carona> caronas2 = new ArrayList<>();
        try {
            connection = Conexao.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM carona WHERE data_viagem = ?");
            ps.setDate(1, Date.valueOf(data));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                caronas.add(dadosDaCarona(rs));
            }
            ps.close();
            connection.close();

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CaronaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Carona carona : caronas) {
            if((carona.getOrigem().distance(origem)*(40075/360) <= 20) && (carona.getDestino().distance(destino)*(40075/360) <= 20)){
                caronas2.add(carona);
            }
        }
        return caronas2;
    }
}
