package br.com.curso.services;

import br.com.curso.adapters.CSVLogAdapter;
import br.com.curso.adapters.JSONLogAdapter;
import br.com.curso.enums.LogType;
import br.com.curso.interfaces.ILog;

import java.util.HashMap;
import java.util.Map;

public final class LogService {

    private static final Map<LogType, ILog> logConfiguration = new HashMap<>();
    private static ILog activeLog = null;

    static {
        logConfiguration.put(LogType.JSON, new JSONLogAdapter());
        logConfiguration.put(LogType.CSV, new CSVLogAdapter());
    }

    private LogService() {
        throw new UnsupportedOperationException("Nao e possivel instanciar uma classe utilitaria.");
    }

    public static ILog definirTipoLog(LogType logType) {
        activeLog = logConfiguration.get(logType);
        return activeLog;
    }

    public static ILog getActiveLog() {
        if (activeLog == null) {
            throw new RuntimeException("Defina um tipo de log.");
        }
        return activeLog;
    }
}