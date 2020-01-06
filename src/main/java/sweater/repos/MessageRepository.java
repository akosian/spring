package sweater.repos;

import org.springframework.data.repository.CrudRepository;
import sweater.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
