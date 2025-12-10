package com.assesment.inventory_management.exception;

public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String message) {
        super(message);
    }
    public DuplicateNameException(String entityType, String name) {
      super(entityType + " with name '" + name + "' already exists");
    }
}
