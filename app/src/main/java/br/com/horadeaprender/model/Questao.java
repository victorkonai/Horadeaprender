package br.com.horadeaprender.model;

import java.util.ArrayList;
import java.util.List;

public class Questao {

    private String enuciado, alternativaCorreta;
    private List<String> alternativas, comentarios;

    public String getEnuciado() {
        return enuciado;
    }

    public Questao(){
        alternativas = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    public void setEnuciado(String enuciado) {
        this.enuciado = enuciado;
    }

    public String getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(String alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        String aux =  "Questao:\n\n" +
                enuciado + "\n\n" ;

        char letra = 'A';

        for (String s: alternativas) {
            aux+= letra + ")" + s + "\n";
            letra++;
        }

        return aux;
    }
}
