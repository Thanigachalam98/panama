package com.trump.panama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.trump.panama.sender.MessageProducer;
import com.trump.panama.message.repository.MessageRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class ChatController {
	
	private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private MessageProducer producer;

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/chatview")
    public String chatView(Model model, Principal principal) {
        model.addAttribute("username", principal != null ? principal.getName() : "Anonymous");
        model.addAttribute("messages", messageRepo.getAllMessages());
        model.addAttribute("message", "");
        return "chat";
    }

    @PostMapping("/chat/send")
    public String sendMessage(@RequestParam("message") String message, Principal principal) {
        String username = principal != null ? principal.getName() : "Anonymous";
        String formattedMessage = username + ": " + message;
        producer.sendMessage(formattedMessage);
        notifyClients(formattedMessage);
        return "redirect:/chatview";
    }
    

    @GetMapping("/chat/stream")
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    private void notifyClients(String message) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
