package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("INFORME O CAMINHO DO ARQUIVO:  ");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        try {
            System.out.println(Operacoes.contarLinhasCodigo(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
