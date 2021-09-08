package springj.authenticas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springj.authenticas.model.TodoList;


@Repository
public interface TodoListRepository
extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByUserUsername(String username);
    Optional<TodoList> findByUserUsernameAndId(String username, long id);
    Optional<TodoList>
        findByUserUsernameAndName(String username, String name);
    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM todo_lists l USING users u " +
                "WHERE l.user_id=u.id AND u.username=?1 AND l.id=?2"
        ,nativeQuery = true
    )
    void deleteByUserUsernameAndId(String username, long id);
}
