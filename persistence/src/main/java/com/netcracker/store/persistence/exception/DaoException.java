package com.netcracker.store.persistence.exception;

/**
 * Created by A-one on 06.06.2017.
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = -7558373872909674084L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
