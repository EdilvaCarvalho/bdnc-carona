
package br.edu.ifpb.bdnc.projeto.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Edilva
 */
public class Sair implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sessao = request.getSession();
        sessao.invalidate();
        try {
            response.sendRedirect("index.html");
        } catch (IOException ex) {
            Logger.getLogger(Sair.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
