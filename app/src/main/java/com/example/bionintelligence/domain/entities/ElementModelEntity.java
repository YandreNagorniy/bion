package com.example.bionintelligence.domain.entities;

public class ElementModelEntity {
    private TypeElementEntity element;
    private int value;

    public ElementModelEntity(TypeElementEntity element, int value) {
        this.element = element;
        this.value = value;
    }

    public TypeElementEntity getElement() {
        return element;
    }

    public void setElement(TypeElementEntity element) {
        this.element = element;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
