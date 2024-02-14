package u5w2d3.u5w2d3.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("L'utente con l'id "+ id + " non è stato trovato");
    }
}
