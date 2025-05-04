package com.koushik.firstproject.config.appLogger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AppLogger {

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT); 
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void info(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void debug(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).debug(message, args);
    }

    public static void error(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).error(message, args);
    }
  
   
  
    public static void dd(Object data) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2]; // Calling method

        String location = caller.getClassName() + "." + caller.getMethodName() + "():" + caller.getLineNumber();
        Logger logger = LoggerFactory.getLogger(caller.getClassName());

        try {
            String prettyJson = mapper.writeValueAsString(data);
            logger.info("\n🟢 dd() at {}\n{}", location, prettyJson);
        } catch (Exception e) {
            logger.info("🟢 dd() at {} => {}", location, data);
        }
    }
    
}


