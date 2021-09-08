package springj.authenticas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springj.authenticas.model.TodoItem;


@Repository
public interface TodoItemRepository
extends JpaRepository<TodoItem, Long> {

    List<TodoItem> findAllByUserUsername(String username);

    Optional<TodoItem> findByUserUsernameAndId(String username, long id);

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM todo_items i USING users u " +
                "WHERE i.user_id=u.id AND u.username=?1 AND i.id=?2"
        ,nativeQuery = true
    )
    void deleteByUserUsernameAndId(String username, long id);

}