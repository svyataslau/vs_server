package com.example.vs_server.model;

public class PromiseDto {

    private Long id;
    private String title;

    public PromiseDto() {
    }

    public PromiseDto(String title) {
        this.title = title;
    }

    public PromiseDto(Long id, String title) {
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
