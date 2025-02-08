package br.com.curso;

import br.com.curso.enums.LogType;
import br.com.curso.interfaces.ILog;
import br.com.curso.services.LogService;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        ILog logAtivo = LogService.definirTipoLog(LogType.CSV);
        logAtivo.registrar("Excluir", "Copo", "Celular");
    }
}