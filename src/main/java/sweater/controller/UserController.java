package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sweater.domain.User;
import sweater.repos.RoleGroupRepository;
import sweater.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roleGroups", roleGroupRepository.findAll());
        return "userEdit";
    }

    @PostMapping("/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String removeUser(@RequestParam String usernameToRemove, Model model) {
        User userToRemove = userService.findByUsername(usernameToRemove);
        if (userToRemove != null) {
            userService.delete(userToRemove);
        } else {
            model.addAttribute("deleteFailMessage", String.format("User with name - %s doesn't exist.", usernameToRemove));
        }
        return userList(model);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form, @RequestParam("userId") User user, Model model) {
        if (userService.findByUsername(username) != null && !username.equals(user.getUsername())) {
            model.addAttribute("validationMessage", "User with this name already created.");
            return userEditForm(user, model);
        } else {
            userService.saveUser(user, username, form);
        }
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String saveProfile(Model model, @AuthenticationPrincipal User user, @RequestParam String password, @RequestParam String email) {
        userService.updateProfile(user,password,email);

        return "redirect:/user/profile";
    }
}