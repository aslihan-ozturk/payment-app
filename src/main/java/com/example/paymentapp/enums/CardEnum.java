package com.example.paymentapp.enums;

public enum CardEnum{
    ACTIVE("A"), CANCELLED("C");

    private final String name;

    private CardEnum(String s){
        name = s;
    }

    public String getValue(){
        return name;
    }
}
