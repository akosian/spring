package sweater.repos;

import org.springframework.data.repository.CrudRepository;
import sweater.domain.RoleGroup;

import java.util.Optional;

public interface RoleGroupRepository extends CrudRepository<RoleGroup, Long> {

    RoleGroup findByRoleGroupName(String roleGroupName);

    Optional<RoleGroup> findById(Long id);
}