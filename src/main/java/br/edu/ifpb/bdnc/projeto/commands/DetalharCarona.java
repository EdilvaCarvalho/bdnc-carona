package br.edu.ifpb.bdnc.projeto.commands;

import br.edu.ifpb.bdnc.projeto.entidades.Carona;
import br.edu.ifpb.bdnc.projeto.services.impl.CaronaServiceImpl;
import br.edu.ifpb.bdnc.projeto.services.interfaces.CaronaService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edilva
 */
public class DetalharCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String c = request.getParameter("c");

        CaronaService service = new CaronaServiceImpl();
        Carona carona = service.getCarona(id);

        request.setAttribute("carona", carona);

        if ("edit".equals(c)) {
            try {
                request.getRequestDispatcher("editarCarona.jsp").forward(request, response);
            } catch (IOException | ServletException ex) {
                Logger.getLogger(DetalharCarona.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                request.getRequestDispatcher("detalhesDaCarona.jsp").forward(request, response);
            } catch (IOException | ServletException ex) {
                Logger.getLogger(DetalharCarona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
