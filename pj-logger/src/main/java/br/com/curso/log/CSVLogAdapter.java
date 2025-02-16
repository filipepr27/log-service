package br.com.curso.log;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class CSVLogAdapter implements ILog {

    private static final String PATH =  "log.csv";
    private static final String DELIMITADOR = ";";

    @Override
    public void registrar(String operacao, String nome, String usuario) {
        LocalDateTime dataAtual = LocalDateTime.now();

        String data = dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = dataAtual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        String[] logEntry = {operacao, nome, usuario, data, hora};
        salvaCSVArquivo(logEntry);
    }

    private void salvaCSVArquivo(String[] logEntry) {
        boolean fileExists = new File(PATH).exists();

        try (CSVWriter writer = new CSVWriter(new FileWriter(PATH, true),
                DELIMITADOR.charAt(0),
                ICSVWriter.NO_QUOTE_CHARACTER,
                ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                ICSVWriter.DEFAULT_LINE_END)) {
            if (!fileExists) {
                String[] header = {"Operação", "Nome", "Usuário", "Data", "Hora"};
                writer.writeNext(header);
            }
            writer.writeNext(logEntry);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o log CSV", e);
        }
    }
}
