package app.bechedhli.solar.exceptions;

public class InterventionNotFoundException extends RuntimeException {
    public InterventionNotFoundException(Long id) {
        super("Intervention not found with id: " + id);
    }
}