package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweater.domain.Message;
import sweater.domain.User;
import sweater.repos.MessageRepository;
import sweater.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;
        List<Message> filterMessages = new ArrayList<>();
        if (!filter.trim().isEmpty()) {
            filterMessages.addAll(messageRepository.findByTag(filter));
            filterMessages.addAll(messageRepository.findByText(filter));
            filterMessages.addAll(messageRepository.findByAuthor(userRepository.findByUsername(filter)));
            messages = filterMessages;
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User author, @RequestParam String text, @RequestParam String tag, Model model) {
        Message message = new Message(text, tag, author);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/delete")
    public String deleteMessage(@RequestParam String messageValue, Model model) {
        if (!messageValue.trim().isEmpty()) {
            int id = Integer.parseInt(messageValue);
            Iterable<Message> messages = messageRepository.findAll();
            AtomicBoolean isMessageExist = new AtomicBoolean(false);
            messages.iterator().forEachRemaining(message -> {
                if (message.getId() == id) {
                    isMessageExist.set(true);
                }
            });
            if (isMessageExist.get()) {
                Message messageToDelete = messageRepository.findById(id).get();
                messageRepository.delete(messageToDelete);
            }
        }
        model.addAttribute("messages", messageRepository.findAll());
        return "redirect:/main";
    }
}