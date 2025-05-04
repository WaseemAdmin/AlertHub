package com.mst.AlertHub.exceptions;

public class UserExceptions {



    public static class UserException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public UserException(String message) {
            super(message);
        }

        public UserException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class UserAlreadyExistsException extends UserException {

        private static final long serialVersionUID = 1L;

        public UserAlreadyExistsException(String message) {
            super(message);
        }

        public UserAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class UserNotFoundException extends UserException {

        private static final long serialVersionUID = 1L;


        public UserNotFoundException(String message) {
            super(message);
        }

        public UserNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidUserDataException extends UserException {

        private static final long serialVersionUID = 1L;


        public InvalidUserDataException(String message) {
            super(message);
        }

        public InvalidUserDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
