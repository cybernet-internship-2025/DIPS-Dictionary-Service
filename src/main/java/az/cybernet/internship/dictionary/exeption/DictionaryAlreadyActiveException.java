package az.cybernet.internship.dictionary.exeption;

public class DictionaryAlreadyActiveException extends RuntimeException {
    public DictionaryAlreadyActiveException(Long id) {
        super("Dictionary with id " + id + " is already active");

    }
}
