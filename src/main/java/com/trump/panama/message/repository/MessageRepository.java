package com.trump.panama.message.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {

    private List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getAllMessages() {  
        return new ArrayList<>(messages); // Ensures the list remains modifiable
    }
}
