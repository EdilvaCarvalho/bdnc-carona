package br.edu.ifpb.bdnc.projeto.commands;

import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import br.edu.ifpb.bdnc.projeto.services.impl.CaronaServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edilva
 */
public class BuscarCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String latOrig = request.getParameter("lat1");
            String lngOrig = request.getParameter("lng1");
            String wktPointOrig = "POINT(" + latOrig + " " + lngOrig + ")";
            WKTReader reader = new WKTReader();
            Point origem = (Point) reader.read(wktPointOrig);;

            String latDest = request.getParameter("lat2");
            String lngDest = request.getParameter("lng2");
            String wktPointDest = "POINT(" + latDest + " " + lngDest + ")";
            Point destino = (Point) reader.read(wktPointDest);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataViagem = LocalDate.parse(request.getParameter("dataViagem"), formatter);

            CaronaService service = new CaronaServiceImpl();
            List<Carona> caronas = service.caronasPesquisa(origem, destino, dataViagem);
            
            request.setAttribute("caronas", caronas);
                try {
                    request.getRequestDispatcher("caronasPesquisadas.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(BuscarCarona.class.getName()).log(Level.SEVERE, null, ex);
                }

        } catch (ParseException ex) {
            Logger.getLogger(BuscarCarona.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
