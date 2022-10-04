package com.exemplo.guests.model;

public class GuestModel {

    private Integer id;
    private String name;
    private String confirmation;


    public GuestModel(Integer id, String name, String confirmation) {
        this.id = id;
        this.name = name;
        this.confirmation = confirmation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String guestConfirmation) {
        this.confirmation = guestConfirmation;
    }
}
