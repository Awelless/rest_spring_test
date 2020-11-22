package com.example.sarafan.controller;

import com.example.sarafan.domain.Message;
import com.example.sarafan.domain.Views;
import com.example.sarafan.dto.EventType;
import com.example.sarafan.dto.ObjectType;
import com.example.sarafan.repo.MessageRepo;
import com.example.sarafan.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(
            @PathVariable("id") Message message
    ) {
        return message;
    }

    @PostMapping
    public Message create(
            @RequestBody Message message
    ) {
        message.setCreatedAt(LocalDateTime.now());
        Message createdMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, createdMessage);

        return createdMessage;
    }

    @PutMapping("/{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id", "createdAt");
        Message updatedMessage = messageRepo.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Message message
    ) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
