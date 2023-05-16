//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 15.05.2023
// 


package ch.es.pl.quotes.api.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Integer idItem) {
        super("Item " + idItem + " non trouvée");
    }
}
