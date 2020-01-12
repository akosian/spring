package sweater.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roleGroup")
public class RoleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleGroupName;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public RoleGroup() {
    }

    public RoleGroup(String roleGroupName, Set<Role> roles) {
        this.roleGroupName = roleGroupName;
        this.roles = roles;
    }
}