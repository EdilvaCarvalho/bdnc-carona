
package br.edu.ifpb.bdnc.projeto.commands;

import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import br.edu.ifpb.bdnc.projeto.entidades.Usuario;
import br.edu.ifpb.bdnc.projeto.services.impl.CaronaServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edilva
 */
public class EditarCarona implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Carona carona;
        try {
            carona = dadosDaCarona(request);

            CaronaService service = new CaronaServiceImpl();
            request.setAttribute("pagina", "caronas.jsp");

            if (service.editar(carona)) {
                request.setAttribute("mensagem", "Carona atualizada com sucesso!");
                try {
                    request.getRequestDispatcher("paginaDeResposta.jsp").forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(EditarCarona.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                request.setAttribute("mensagem", "Erro ao atualizar carona!");
                try {
                    request.getRequestDispatcher("paginaDeResposta.jsp").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(EditarCarona.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(EditarCarona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Carona dadosDaCarona(HttpServletRequest request) throws ParseException {
        
        Carona carona = new Carona();
        
        HttpSession sessao = request.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        carona.setUsuario(usuario);

        String latOrig = request.getParameter("lat1");
        String lngOrig = request.getParameter("lng1");
        String wktPointOrig = "POINT("+latOrig+" "+lngOrig+")";
        WKTReader reader = new WKTReader();
        Point origem = (Point) reader.read(wktPointOrig);
        carona.setOrigem(origem);

        String latDest = request.getParameter("lat2");
        String lngDest = request.getParameter("lng2");
        String wktPointDest = "POINT("+latDest+" "+lngDest+")";
        Point destino = (Point) reader.read(wktPointDest);
        carona.setDestino(destino);

        if (request.getParameter("dataViagem") != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataViagem = LocalDate.parse(request.getParameter("dataViagem"), formatter);
            carona.setDataViagem(dataViagem);
        }

        if (request.getParameter("horaSaida") != null) {
            LocalTime horaSaida = LocalTime.parse(request.getParameter("horaSaida"));
            carona.setHoraSaida(horaSaida);
        }

        if (request.getParameter("custo") != null) {
            double custo = Double.parseDouble(request.getParameter("custo"));
            carona.setAjudaCusto(custo);
        }
        
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            carona.setId(id);
        }
        
        if (request.getParameter("distancia") != null) {
            String distancia = request.getParameter("distancia");
            carona.setDistancia(distancia);
        }

        if (request.getParameter("duracao") != null) {
            String duracao = request.getParameter("duracao");
            carona.setDuracao(duracao);
        }
        
        if (request.getParameter("origem") != null) {
            String origemTxt = request.getParameter("origem");
            carona.setOrigemTxt(origemTxt);
        }
        
        if (request.getParameter("destino") != null) {
            String destinoTxt = request.getParameter("destino");
            carona.setDestinoTxt(destinoTxt);
        }

        return carona;
    }
    
}
