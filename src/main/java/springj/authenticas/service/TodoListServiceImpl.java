package springj.authenticas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springj.authenticas.exception.BadRequestException;
import springj.authenticas.exception.ResourceNotFoundException;
import springj.authenticas.model.TodoList;
import springj.authenticas.payload.TodoListRequest;
import springj.authenticas.repository.TodoListRepository;
import springj.authenticas.repository.UserRepository;


@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoListServiceImpl(
        TodoListRepository todoListRepository
        ,UserRepository userRepository
    ) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TodoList> getAll(String username) {
        return todoListRepository.findAllByUserUsername(username);
    }

    @Override
    public TodoList getOne(String username, long id) {
        return todoListRepository.findByUserUsernameAndId(username, id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "TodoList", "id", id
            ))
        ;
    }

    @Override
    public TodoList create(String username, TodoListRequest newObj) {
        TodoList o = new TodoList();
        if (newObj.getName()!=null) {
            o.setName(newObj.getName());
        } else {
            throw new BadRequestException(
                "TodoList 'name' required."
            );
        }
        o.setUser(userRepository.findByUsername(username));
        return todoListRepository.save(o);
    }

    @Override
    public TodoList update(String username, long id, TodoListRequest newObj) {
        TodoList o = todoListRepository
            .findByUserUsernameAndId(username, id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "TodoList", "id", id
            ))
        ;
        if (newObj.getName()!=null) {
            o.setName(newObj.getName());
        }
        return todoListRepository.save(o);
    }

    @Override
    public void delete(String username, long id) {
        todoListRepository.deleteByUserUsernameAndId(username, id);
    }

}
