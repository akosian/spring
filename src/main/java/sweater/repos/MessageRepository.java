package sweater.repos;

import org.springframework.data.repository.CrudRepository;
import sweater.domain.Message;
import sweater.domain.User;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

    List<Message> findByText(String text);

    List<Message> findByAuthor(User author);

    Optional<Message> findById(Integer id);
}