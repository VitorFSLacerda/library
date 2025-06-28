package utils;
import java.text.Normalizer;

public class StringUtils {
    public static String normalize(String input) {
        if (input == null) return null;

        // Remove acentos
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Converte para minúsculas
        normalized = normalized.toLowerCase();

        return normalized;
    }
}

