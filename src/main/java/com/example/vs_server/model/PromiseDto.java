package com.example.vs_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PromiseDto {

    private Long id;
    private String title;

    public PromiseDto(String title) {
        this.title = title;
    }
}
