// package com.SingleTon;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Colegiado {
    private static Colegiado instancia;
    private Integer brancos = 0;
    private Integer nulos = 0;
    protected Map<Integer,Candidato> candidatos;

    public static int VOTO_BRANCO = 9999;
    public static int VOTO_NULO = 9998;

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
        System.out.format("| BRANCOS                             | %5d |\n", brancos);
        System.out.format("| NULOS                               | %5d |\n", nulos	);
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
        if (codigo == VOTO_BRANCO) {
            brancos += numeroVotos;
        } else if (codigo == VOTO_NULO) {
            nulos += numeroVotos;
        } else {
            Candidato candidato = candidatos.get(codigo);
            candidato.adicionarVotos(numeroVotos);
        }
    }
}
