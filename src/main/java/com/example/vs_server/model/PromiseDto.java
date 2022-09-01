package com.example.vs_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromiseDto {

    private Long id;
    private String title;

    public PromiseDto(String title) {
        this.title = title;
    }
}
