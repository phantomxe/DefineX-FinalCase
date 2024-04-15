package com.patika.garantiservice.exceptions.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class KafkaLog implements Serializable {
    private String message;
    private String description;
    private final String date = LocalDateTime.now().toString();
}
