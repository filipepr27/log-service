package br.com.curso.log;


import java.util.HashMap;
import java.util.Map;

public final class LogService {

    private static final Map<LogType, ILog> logConfiguration = new HashMap<>();
    private ILog activeLog = null;

    static {
        logConfiguration.put(LogType.JSON, new JSONLogAdapter());
        logConfiguration.put(LogType.CSV, new CSVLogAdapter());
    }

    public LogService(LogType logType) {
        validarLog(logType);
        activeLog = logConfiguration.get(logType);
    }

    public void alterarTipoLog(LogType logType) {
        validarLog(logType);
        activeLog = logConfiguration.get(logType);
    }

    public void registrarLog(String operacao, String nome, String usuario) {
        if (activeLog == null) {
            throw new RuntimeException("Defina um tipo de log.");
        }
        activeLog.registrar(operacao, nome, usuario);
    }

    private void validarLog(LogType logType) {
        if (logType == null)
            throw new RuntimeException("Defina um tipo de log válido.");
    }
}