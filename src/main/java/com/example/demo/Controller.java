package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class Controller {
    Map<String, List<Message>> messages = new HashMap<>();
    /* DATABASE (HASH MAP)
     Messages {
        "to": [{message}, {message}],
    } */

    /*
    * message (MODEL)
    * {
    *   to: "userId-1",
    *   from: "userId-2",
    *   content: "Hello"
    * }
    *  */


    @GetMapping("/{chatId}")
    ResponseEntity<List<Message>> getMessages(@PathVariable String chatId) {
        List<Message> messages = this.messages.get(chatId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/")
    ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        messages.putIfAbsent(message.to(), List.of(message));
        messages.computeIfPresent(message.to(), (key, messages) -> {
            List<Message> newList = new ArrayList<Message>(messages);
            newList.add(message);
            return newList;
        });
        return ResponseEntity.ok(message);
    }
}
