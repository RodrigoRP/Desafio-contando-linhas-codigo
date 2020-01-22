package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Operacoes {

    public static int contarLinhasCodigo(String path) throws IOException {
        Boolean flag = Boolean.TRUE;
        int count = 0; // Conta as linhas em branco e comentarios
        int posicaoInicial = 0;
        int posicaoFinal = 0;
        List<String> conteudoArquivo = lerArquivo(path);

        for (int i = 0; i < conteudoArquivo.size(); i++) {
            if (conteudoArquivo.get(i).isEmpty()) {
                count++;
            }
            if (conteudoArquivo.get(i).length() > 1) {
                if (conteudoArquivo.get(i).charAt(0) == '/' && conteudoArquivo.get(i).charAt(1) == '/') {
                    count++;
                }
                if (conteudoArquivo.get(i).contains("/*") && flag == Boolean.TRUE) {
                    posicaoInicial = i;
                    flag = Boolean.FALSE;
                }
                if (conteudoArquivo.get(i).contains("*/")) {
                    flag = Boolean.TRUE;
                    posicaoFinal = i;
                    count = count + (posicaoFinal - (posicaoInicial + 1)) + 1;
                }
            }
        }
        return conteudoArquivo.size() - count;
    }

    public static List<String> lerArquivo(String path) throws IOException {
        List<String> linhasSemEspaco = null;
        try {
            List<String> linhas = Files.readAllLines(Paths.get(path));
            linhasSemEspaco =
                    linhas.stream().map(String::trim).collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            throw new NoSuchFileException("Caminho nao encontrado!!");
        }
        return linhasSemEspaco;
    }
}
