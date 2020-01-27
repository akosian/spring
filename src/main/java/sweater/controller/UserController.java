package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sweater.domain.Role;
import sweater.domain.RoleGroup;
import sweater.domain.User;
import sweater.repos.RoleGroupRepository;
import sweater.repos.UserRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roleGroups", roleGroupRepository.findAll());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            Model model) {
        if (userRepository.findByUsername(username) != null && !username.equals(user.getUsername())) {
            model.addAttribute("validationMessage", "User with this name already created.");
            return userEditForm(user, model);
        } else {
            user.setUsername(username);
            user.getRoles().clear();
            Iterable<RoleGroup> roleGroups = roleGroupRepository.findAll();
            Set<String> roleGroupToSet = new HashSet<>();
            for (String key : form.keySet()) {
                roleGroups.iterator().forEachRemaining((roleGroup -> {
                    String roleGroup1 = roleGroup.getRoleGroupName();
                    String expectedRoleGroup = key;
                    if (roleGroup1.equals(expectedRoleGroup)) {
                        user.getRoleGroups().add(roleGroup.getRoleGroupName());
                        user.getRoles().addAll(roleGroup.getRoles());
                    }
                }));
            }
            user.setRoleGroups(roleGroupToSet);
            userRepository.save(user);
            return "redirect:/user";
        }
    }
}