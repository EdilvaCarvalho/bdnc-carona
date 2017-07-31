/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.projeto.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Edilva
 */
public class DataFormatada extends SimpleTagSupport{
    private LocalDate data;
    
    @Override
    public void doTag() throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = dtf.format(data);
        this.getJspContext().getOut().print(resultado);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
}
