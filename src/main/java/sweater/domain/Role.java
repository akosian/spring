package sweater.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    public static Set<String> getRoles() {
        return Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
