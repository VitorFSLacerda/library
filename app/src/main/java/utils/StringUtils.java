package utils;
import java.text.Normalizer;

/**
 * Utility class for common string operations.
 * <p>
 * Provides methods to normalize strings by removing accents and converting to lowercase.
 * </p>
 */
public class StringUtils {
    /**
     * Normalizes the given string by removing diacritical marks (accents) and converting all characters to lowercase.
     * <p>
     * This method first applies Unicode normalization (NFD form) to decompose characters,
     * then removes all combining diacritical marks using a regular expression,
     * and finally converts the result to lowercase.
     * </p>
     *
     * @param input the string to be normalized; may be {@code null}
     * @return the normalized string without diacritical marks and in lowercase,
     *         or {@code null} if the input is {@code null}
     */
    public static String normalize(String input) {
        if (input == null) return null;

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalized = normalized.toLowerCase();
        return normalized;
    }
}

