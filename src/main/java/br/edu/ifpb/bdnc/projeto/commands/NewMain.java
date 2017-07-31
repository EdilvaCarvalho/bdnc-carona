/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.projeto.commands;

import br.edu.ifpb.bdnc.projeto.dao.impl.UsuarioDaoImpl;
import br.edu.ifpb.bdnc.projeto.dao.interfaces.UsuarioDAO;
import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import br.edu.ifpb.bdnc.projeto.entidades.Sexo;
import br.edu.ifpb.bdnc.projeto.entidades.Usuario;
import br.edu.ifpb.bdnc.projeto.services.impl.CaronaServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Edilva
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
//        UsuarioDAO usuarioDAO = new UsuarioDaoImpl();
//        //Usuario u = new Usuario("edilva", "edilva@gmail.com", "1234", "99999999", LocalDate.now(), Sexo.FEMININO);
//        Usuario u = usuarioDAO.getUsuario(1);
//        System.out.println(u.toString());
        WKTReader reader = new WKTReader();
        Point geometry = (Point) reader.read("POINT (-6.889707100000001 -38.561218499999995)");
        Point geometry2 = (Point) reader.read("POINT (-6.7518873 -38.23140219999999)");
        System.out.println(geometry.distance(geometry2)*(40075/360));
       // Carona carona = new Carona(geometry, geometry2, LocalDate.now(), LocalTime.now(), "50 min", 10, "100 km", u);
    
//        CaronaService service = new CaronaServiceImpl();
//        //service.salvar(carona);
//        System.out.println(service.getCarona(1));
//        System.out.println(service.caronasPesquisa(origem, destino, LocalDate.MAX));
    }

}
