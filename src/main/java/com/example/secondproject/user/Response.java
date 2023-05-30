package com.example.secondproject.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Response<T> {
    private boolean succes;
    private String messege;
    private int code;
    private T data;
}
