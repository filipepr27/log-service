package br.com.curso;

import br.com.curso.log.LogService;
import br.com.curso.log.LogType;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        LogService logAtivo = new LogService(LogType.CSV);
        logAtivo.registrarLog("Excluir", "Copo", "Celular");
    }
}