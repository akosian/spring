package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweater.domain.Role;
import sweater.domain.RoleGroup;
import sweater.repos.RoleGroupRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class CreateRoleGroupController {
    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @GetMapping("/createRole")
    public String createRole(Model model) {
        model.addAttribute("roles", Role.values());
        return "createRole";
    }

    @PostMapping("/createRole")
    public String saveRoleGroup(@RequestParam String roleGroupName, @RequestParam Map<String, String> form, Model model) {
        RoleGroup roleGroupFromDb = roleGroupRepository.findByRoleGroupName(roleGroupName);
        if (roleGroupFromDb != null) {
            model.addAttribute("message", "Role group with this name already used!");
            model.addAttribute("roles", Role.values());
            return "createRole";
        }
        Set<Role> rolesToSet = new HashSet<>();
        for (String key : form.keySet()) {
            if (Role.getRoles().contains(key)) {
                rolesToSet.add(Role.valueOf(key));
            }
        }
        roleGroupRepository.save(new RoleGroup(roleGroupName, rolesToSet));
        return "redirect:/main";
    }
}