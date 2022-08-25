package com.example.vs_server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Promise {
    private Long id;
    private String title;

    public Promise(String title) {
        this.title = title;
    }

}
