package app.bechedhli.solar.exceptions;

public class PieceNotFoundException extends RuntimeException {
    public PieceNotFoundException(Long id) {
        super("Piece not found with id: " + id);
    }
}