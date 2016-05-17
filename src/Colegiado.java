// package com.SingleTon;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Colegiado {
    private static Colegiado instancia;
    protected Map<Integer,Candidato> candidatos;

    Colegiado() {
        candidatos = new HashMap<Integer,Candidato>();
    }

    public static synchronized Colegiado getInstance() {
        if (instancia == null) {
            instancia = new Colegiado();
        }
        return instancia;
    }

    public String listarCandidatos() {
        Candidato candidato = null;
        String string = "";
        for ( Integer codigo : candidatos.keySet() ) {
            string += candidatos.get(codigo).toString() + "\n";
        }
        return string;
    }

    public void printarCandidatos() {
        Candidato candidato = null;
        System.out.print("-----------------------------------------------\n");
        // System.out.print("-----------------------------------------------\n");
        System.out.print("| ID | Partido |   Nome do Candidato  | Votos |\n");
        System.out.print("-----------------------------------------------\n");
        for ( Integer codigo : candidatos.keySet() ) {
            candidato = candidatos.get(codigo);
            System.out.format("| %2d | %7s | %20s | %5d |\n",
                candidato.getCodigo(), candidato.getPartido(),
                candidato.getNome(), candidato.getNumeroVotos()
            );
        }
        System.out.print("-----------------------------------------------\n");
    }

    public void putCandidato(Candidato candidato) {
        candidatos.put(candidato.codigo, candidato);
    }

    public void removeCandidato(Integer codigo) {
        candidatos.remove(codigo);
    }

    public Candidato procurarCandidato(Integer codigo) {
        return candidatos.get(codigo);
    }

    public void computarVotos(Integer codigo, Integer numeroVotos) {
        Candidato candidato = candidatos.get(codigo);
        candidato.adicionarVotos(numeroVotos);
    }
}
