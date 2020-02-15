package sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sweater.domain.Role;
import sweater.domain.RoleGroup;
import sweater.repos.RoleGroupRepository;

import java.util.Map;

@Controller
@RequestMapping("/roleGroup")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleGroupController {
    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @GetMapping
    public String roleGroupList(Model model) {
        model.addAttribute("roleGroups", roleGroupRepository.findAll());
        return "roleGroupList";
    }

    @GetMapping("{roleGroup}")
    public String roleGroupEdit(@PathVariable RoleGroup roleGroup, Model model) {
        model.addAttribute("roleGroup", roleGroup);
        model.addAttribute("roles", Role.values());
        return "roleGroupEdit";
    }

    @PostMapping("/remove")
    public String removeRoleGroup(@RequestParam String roleGroupNameToRemove, Model model) {
        RoleGroup roleGroupToRemove = roleGroupRepository.findByRoleGroupName(roleGroupNameToRemove);
        if (roleGroupToRemove != null) {
            roleGroupRepository.delete(roleGroupToRemove);
        } else {
            model.addAttribute("deleteFailMessage", String.format("Role group with name - %s doesn't exist.", roleGroupNameToRemove));
        }
        return roleGroupList(model);
    }

    @PostMapping
    public String saveRoleGroup(
            @RequestParam String roleGroupName,
            @RequestParam Map<String, String> form,
            @RequestParam("roleGroupId") RoleGroup roleGroup,
            Model model) {
        if (roleGroupRepository.findByRoleGroupName(roleGroupName) != null && !roleGroup.getRoleGroupName().equals(roleGroupName)) {
            model.addAttribute("message", "Role Group with this name already exist.");
            return roleGroupEdit(roleGroup, model);
        } else {
            roleGroup.setRoleGroupName(roleGroupName);
            roleGroup.getRoles().clear();
            for (String key : form.keySet()) {
                if (Role.getRoles().contains(key)) {
                    roleGroup.getRoles().add(Role.valueOf(key));
                }
            }
            roleGroupRepository.save(roleGroup);
            return "redirect:/roleGroup";
        }
    }
}
