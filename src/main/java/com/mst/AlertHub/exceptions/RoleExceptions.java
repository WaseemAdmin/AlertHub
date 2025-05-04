package com.mst.AlertHub.exceptions;

public class RoleExceptions {


    public static class RoleException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public RoleException(String message) {
            super(message);
        }

        public RoleException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class RoleAlreadyExistsException extends RoleException {

        private static final long serialVersionUID = 1L;

        public RoleAlreadyExistsException(String message) {
            super(message);
        }

        public RoleAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class RoleNotFoundException extends RoleException {

        private static final long serialVersionUID = 1L;

        public RoleNotFoundException(String message) {
            super(message);
        }

        public RoleNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidRoleDataException extends RoleException {

        private static final long serialVersionUID = 1L;

        public InvalidRoleDataException(String message) {
            super(message);
        }

        public InvalidRoleDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
