package springj.authenticas.service;

import java.util.List;

import springj.authenticas.model.TodoItem;
import springj.authenticas.payload.TodoItemRequest;


public interface TodoItemService {
    public List<TodoItem> getAll(String username);
    public TodoItem getOne(String username, long id);
    public TodoItem create(String username, TodoItemRequest newObj);
    public TodoItem update(String username, long id, TodoItemRequest newObj);
    public void delete(String username, long id);
}
