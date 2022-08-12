package com.example.vs_server.model;

public class Promise {
    private Long id;
    private String title;

    public Promise() {
    }

    public Promise(String title) {
        this.title = title;
    }

    public Promise(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
