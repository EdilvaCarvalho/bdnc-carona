package br.edu.ifpb.bdnc.projeto.commands;

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
public class ExcluirCarona implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        CaronaService service = new CaronaServiceImpl();
        String url = request.getHeader("referer");
        request.setAttribute("pagina", url);

        try {
            if (service.remover(id)) {
                request.setAttribute("mensagem", "Carona removida com sucesso!");
                request.getRequestDispatcher("paginaDeResposta.jsp").forward(request, response);
            } else {
                request.setAttribute("mensagem", "Erro ao remover carona!");
                request.getRequestDispatcher("paginaDeResposta.jsp").forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ExcluirCarona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
