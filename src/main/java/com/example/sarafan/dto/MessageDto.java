package com.example.sarafan.dto;

import com.example.sarafan.domain.Comment;
import com.example.sarafan.domain.User;
import com.example.sarafan.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessageDto {

    private Long id;
    private String text;

    private LocalDateTime createdAt;

    private User author;

    private List<Comment> comments;

    private String link;
    private String linkTitle;
    private String linkDescription;
    private String linkCover;
}
