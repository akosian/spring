package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sweater.domain.User;
import sweater.repos.UserRepository;

@Controller
public class RegistrationController {
    @Autowired
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User exist!");
            return "registration";
        }
        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));//todo
        userRepository.save(user);
        return "redirect:/login";
    }
}