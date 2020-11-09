package com.example.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @JsonView(Views.Id.class)
    private Long id;
    @NonNull
    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd, HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime createdAt;
}