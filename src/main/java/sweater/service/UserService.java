package sweater.service;

import freemarker.template.utility.StringUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sweater.domain.Role;
import sweater.domain.RoleGroup;
import sweater.domain.User;
import sweater.repos.RoleGroupRepository;
import sweater.repos.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleGroupRepository roleGroupRepository;
    private final MailSender mailSender;

    public UserService(UserRepository userRepository, MailSender mailSender, RoleGroupRepository roleGroupRepository) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.roleGroupRepository = roleGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        sendEmail(user);
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String usernameToRemove) {
        return userRepository.findByUsername(usernameToRemove);
    }

    public void delete(User userToRemove) {
        userRepository.delete(userToRemove);
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        user.getRoles().clear();
        Iterable<RoleGroup> roleGroups = roleGroupRepository.findAll();
        Set<String> roleGroupToSet = new HashSet<>();
        for (String key : form.keySet()) {
            roleGroups.iterator().forEachRemaining((roleGroup -> {
                String roleGroup1 = roleGroup.getRoleGroupName();
                if (roleGroup1.equals(key)) {
                    user.getRoleGroups().add(roleGroup.getRoleGroupName());
                    user.getRoles().addAll(roleGroup.getRoles());
                }
            }));
            user.setRoleGroups(roleGroupToSet);
            userRepository.save(user);
        }
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equalsIgnoreCase(userEmail) || (userEmail != null && !userEmail.equalsIgnoreCase(email)));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        userRepository.save(user);

        if (isEmailChanged) {
            sendEmail(user);
        }
    }

    private void sendEmail(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello %s! \n" +
                            " please activate your account by following link http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode());
            mailSender.send(user.getEmail(), "Activate your Sweater account", message);
        }
    }
}