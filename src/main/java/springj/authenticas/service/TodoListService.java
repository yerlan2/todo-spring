package springj.authenticas.service;

import java.util.List;

import springj.authenticas.model.TodoList;
import springj.authenticas.payload.TodoListRequest;


public interface TodoListService {
    public List<TodoList> getAll(String username);
    public TodoList getOne(String username, long id);
    public TodoList create(String username, TodoListRequest newObj);
    public TodoList update(String username, long id, TodoListRequest newObj);
    public void delete(String username, long id);
}
