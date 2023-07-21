package ru.warehouse.socks.exeption;

public class NoRequiredQuantityException extends RuntimeException {

    private final long id;

    public NoRequiredQuantityException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Socks with id = " + id + " no required quantity";
    }

}
