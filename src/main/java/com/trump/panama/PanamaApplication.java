package com.trump.panama;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trump.panama.models.Product;

@SpringBootApplication
@EnableKafka
@Controller
public class PanamaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanamaApplication.class, args);
	}
	
	@GetMapping("/")
    public String login() {
        return "login"; // This refers to the index.html Thymeleaf template
    }
	
	@GetMapping("/home")
    public String index() {
        return "index"; // This refers to the index.html Thymeleaf template
    }
	
	@GetMapping("/about")
    public String about() {
        return "about"; // This refers to the index.html Thymeleaf template
    }
	
	@GetMapping("/registerview")
    public String register() {
        return "register"; // This refers to the index.html Thymeleaf template
    }
    
    @GetMapping("/shopview")
    public String shop(Model model) {
    	List<Product> products = Arrays.asList(
                new Product("Stylish T-Shirt", 29.99, "/images/tshirt1.jpg"),
                new Product("Elegant Dress", 49.99, "/images/Dress.jpg"),
                new Product("Casual Jeans", 39.99, "/images/Jeans1.jpg")
            );
            
            model.addAttribute("products", products);
        return "shop"; // Future shop page (you can create this later)
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

//    @PostMapping("/contact")
//    public String handleContactForm(@RequestParam String name,
//                                    @RequestParam String email,
//                                    @RequestParam String message) {
//        // TODO: Save to DB, send email, Kafka log, etc.
//        System.out.println("Contact received: " + name + ", " + email + ", " + message);
//        return "redirect:/contact?success";
//    }
    
//    @GetMapping("/chatview")
//    public String chat() {
//        return "chat"; // This refers to the chat.html Thymeleaf template
//    }
}
