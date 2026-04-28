package app.bechedhli.solar.exceptions;

public class StockInsuffisantException extends RuntimeException {
    public StockInsuffisantException(Long pieceId, int demande, int disponible) {
        super("Stock insuffisant pour la pièce " + pieceId + ". Demandé: " + demande + ", Disponible: " + disponible);
    }
}