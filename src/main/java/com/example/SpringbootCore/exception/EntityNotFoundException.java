package com.example.SpringbootCore.exception;

public class EntityNotFoundException extends RuntimeException {
    private String entity;

    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with id " + id + " not found");
        this.entity = entity;
    }

    public EntityNotFoundException(String message) {
        super(message);this.entity = "Entity";
    }

    public String getEntity() {
        return entity;
    }
}
