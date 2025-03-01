package br.com.curso.log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

final class JSONLogAdapter implements ILog {

    private static final String PATH = "log.json";

    @Override
    public void registrar(String operacao, String nome, String usuario) {
        LocalDateTime dataAtual = LocalDateTime.now();

        JSONObject logJson = new JSONObject();
        logJson.put("Operação", operacao);
        logJson.put("Nome", nome);
        logJson.put("Usuário", usuario);
        logJson.put("Data", dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        logJson.put("Hora", dataAtual.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        salvaJSONArquivo(logJson);
    }

    private void salvaJSONArquivo(JSONObject logJson){
        try {
            File file = new File(PATH);
            if (!file.exists()) {
                if(!file.createNewFile())
                    throw new Exception("Não foi possível criar o arquivo log");

                JSONObject logsJson = new JSONObject();
                logsJson.put("logs", new JSONArray().put(logJson));

                try (FileWriter writer = new FileWriter(PATH)) {
                    writer.write(logsJson.toString(4));
                }
            } else {
                try (FileReader reader = new FileReader(PATH);
                     Scanner scanner = new Scanner(reader)) {

                    StringBuilder content = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        content.append(scanner.nextLine());
                    }

                    JSONObject logsJson = new JSONObject(content.toString());
                    logsJson.getJSONArray("logs").put(logJson);

                    try (FileWriter writer = new FileWriter(PATH)) {
                        writer.write(logsJson.toString(4));
                    }
                }
            }
            System.out.println("Log adicionado com sucesso!");

        }  catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o log JSON", e);
        }
    }
}
