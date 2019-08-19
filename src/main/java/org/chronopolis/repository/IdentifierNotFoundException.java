package org.chronopolis.repository;


public class IdentifierNotFoundException extends RuntimeException {

    public IdentifierNotFoundException(String identifier) {
        super("Identifier not found: '" + identifier + "'");
    }
}
