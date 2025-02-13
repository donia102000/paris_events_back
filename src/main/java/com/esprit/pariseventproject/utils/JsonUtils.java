package com.esprit.pariseventproject.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String getSafeText(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asText() : null;
    }

    public static Double getSafeDouble(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asDouble() : null;
    }

    public static Integer getSafeInteger(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asInt() : null;
    }

    public static LocalDateTime getSafeLocalDateTime(JsonNode node, String fieldName) {
        if (node.has(fieldName) && !node.get(fieldName).isNull()) {
            try {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(node.get(fieldName).asText());
                return offsetDateTime.toLocalDateTime();
            } catch (DateTimeParseException e) {
                logger.error("Erreur de parsing de la date: " + node.get(fieldName).asText(), e);
            }
        }
        return null;
    }

    public static Boolean getSafeBoolean(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asBoolean() : null;
    }
}
