package springj.authenticas.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springj.authenticas.model.TodoItem;
import springj.authenticas.model.TodoViews;
import springj.authenticas.payload.TodoItemRequest;
import springj.authenticas.service.TodoItemService;


@RestController
@RequestMapping("/api/items")
public class TodoItemController {

    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @JsonView({TodoViews.TodoItem.class})
    @GetMapping
    public List<TodoItem> all() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return todoItemService.getAll(username);
    }

    @GetMapping("/{id}")
    public TodoItem one(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return todoItemService.getOne(username, id);
    }

    @PostMapping
    public TodoItem addNew(@RequestBody TodoItemRequest newObj) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return todoItemService.create(username, newObj);
    }

    @PutMapping("/{id}")
    public TodoItem replace(
        @PathVariable long id,
        @RequestBody TodoItemRequest newObj
    ) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return todoItemService.update(username, id, newObj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        todoItemService.delete(username, id);
    }

}
