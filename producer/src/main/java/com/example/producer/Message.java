package com.example.producer;


import lombok.*;

import java.io.Serializable;

@Builder
@Setter(AccessLevel.PUBLIC)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {


    private String message;
    private Long id;


}
