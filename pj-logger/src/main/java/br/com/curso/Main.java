package br.com.curso;

import br.com.curso.log.LogService;
import br.com.curso.log.LogType;

class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        LogService logAtivo = new LogService(LogType.JSON);
        logAtivo.registrarLog("Excluir", "Copo", "Celular");
    }
}