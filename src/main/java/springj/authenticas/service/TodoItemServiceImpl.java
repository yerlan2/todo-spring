package springj.authenticas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springj.authenticas.exception.BadRequestException;
import springj.authenticas.exception.ResourceNotFoundException;
import springj.authenticas.model.PriorityEnum;
import springj.authenticas.model.StatusEnum;
import springj.authenticas.model.TodoItem;
import springj.authenticas.payload.TodoItemRequest;
import springj.authenticas.repository.TodoItemRepository;
import springj.authenticas.repository.TodoListRepository;
import springj.authenticas.repository.UserRepository;


@Service
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoItemServiceImpl(
        TodoItemRepository todoItemRepository
        ,TodoListRepository todoListRepository
        ,UserRepository userRepository
    ) {
        this.todoItemRepository = todoItemRepository;
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TodoItem> getAll(String username) {
        return todoItemRepository.findAllByUserUsername(username);
    }

    @Override
    public TodoItem getOne(String username, long id) {
        return todoItemRepository.findByUserUsernameAndId(username, id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "TodoItem", "id", id
            ))
        ;
    }

    @Override
    public TodoItem create(String username, TodoItemRequest newObj) {
        TodoItem o = new TodoItem();
        if (newObj.getTitle()!=null) {
            o.setTitle(newObj.getTitle());
        } else {
            throw new BadRequestException(
                "TodoItem 'title' required."
            );
        }
        if (newObj.getDescription()!=null) {
            o.setDescription(newObj.getDescription());
        } else {
            o.setDescription("");
        }
        if (newObj.getPriority()!=null) {
            o.setPriority(newObj.getPriority());
        } else {
            o.setPriority(PriorityEnum.PRIORITY_NO);
        }
        if (newObj.getTodoList()!=null) {
            o.setTodoList(todoListRepository
                .findByUserUsernameAndName(
                    username, newObj.getTodoList()
                )
                .orElseThrow(() -> new ResourceNotFoundException(
                    "TodoList", "name", newObj.getTodoList()
                ))
            );
        }
        o.setStatus(StatusEnum.STATUS_PLANNED);
        o.setUser(userRepository.findByUsername(username));
        return todoItemRepository.save(o);
    }

    @Override
    public TodoItem update(String username, long id, TodoItemRequest newObj) {
        TodoItem o = todoItemRepository
            .findByUserUsernameAndId(username, id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "TodoItem", "id", id
            ))
        ;
        if (newObj.getTitle()!=null) {
            o.setTitle(newObj.getTitle());
        }
        if (newObj.getDescription()!=null) {
            o.setDescription(newObj.getDescription());
        }
        if (newObj.getTodoList()!=null) {
            o.setTodoList(todoListRepository
                .findByUserUsernameAndName(username, newObj.getTodoList())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "TodoList", "name", newObj.getTodoList()
                ))
            );
        }
        return todoItemRepository.save(o);
    }

    @Override
    public void delete(String username, long id) {
        todoItemRepository.deleteByUserUsernameAndId(username, id);
    }
}
