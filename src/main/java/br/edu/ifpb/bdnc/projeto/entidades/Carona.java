package br.edu.ifpb.bdnc.projeto.entidades;

import com.vividsolutions.jts.geom.Point;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Edilva
 */
public class Carona implements Serializable {

    private int id;
    private String origemTxt;
    private String destinoTxt;
    private Point origem;
    private Point destino;
    private LocalDate dataViagem;
    private LocalTime horaSaida;
    private String duracao;
    private double ajudaCusto;
    private String distancia;
    private Usuario usuario;

    public Carona(int id, String origemTxt, String destinoTxt, Point origem, Point destino, LocalDate dataViagem, LocalTime horaSaida, String duracao, double ajudaCusto, String distancia, Usuario usuario) {
        this.id = id;
        this.origemTxt = origemTxt;
        this.destinoTxt = destinoTxt;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.horaSaida = horaSaida;
        this.duracao = duracao;
        this.ajudaCusto = ajudaCusto;
        this.distancia = distancia;
        this.usuario = usuario;
    }

    public Carona(String origemTxt, String destinoTxt, Point origem, Point destino, LocalDate dataViagem, LocalTime horaSaida, String duracao, double ajudaCusto, String distancia, Usuario usuario) {
        this.origemTxt = origemTxt;
        this.destinoTxt = destinoTxt;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.horaSaida = horaSaida;
        this.duracao = duracao;
        this.ajudaCusto = ajudaCusto;
        this.distancia = distancia;
        this.usuario = usuario;
    }

    public Carona() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigemTxt() {
        return origemTxt;
    }

    public void setOrigemTxt(String origemTxt) {
        this.origemTxt = origemTxt;
    }

    public String getDestinoTxt() {
        return destinoTxt;
    }

    public void setDestinoTxt(String destinoTxt) {
        this.destinoTxt = destinoTxt;
    }

    public Point getOrigem() {
        return origem;
    }

    public void setOrigem(Point origem) {
        this.origem = origem;
    }

    public Point getDestino() {
        return destino;
    }

    public void setDestino(Point destino) {
        this.destino = destino;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public double getAjudaCusto() {
        return ajudaCusto;
    }

    public void setAjudaCusto(double ajudaCusto) {
        this.ajudaCusto = ajudaCusto;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carona other = (Carona) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carona{" + "id=" + id + ", origemTxt=" + origemTxt + ", destinoTxt=" + destinoTxt + ", origem=" + origem + ", destino=" + destino + ", dataViagem=" + dataViagem + ", horaSaida=" + horaSaida + ", duracao=" + duracao + ", ajudaCusto=" + ajudaCusto + ", distancia=" + distancia + ", usuario=" + usuario + '}';
    }

}
