package com.journal.repository.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerUtil {
    public static void initLogManager() {
        try {
            LogManager.getLogManager().readConfiguration( new FileInputStream("src/main/resources/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
