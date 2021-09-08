package springj.authenticas.controller;

import java.net.URI;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.http.ResponseEntity;
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

import springj.authenticas.model.TodoList;
import springj.authenticas.model.TodoViews;
import springj.authenticas.payload.TodoListRequest;
import springj.authenticas.service.TodoListService;


@RestController
@RequestMapping("/api/lists")
public class TodoListController {

    private final TodoListService todoListService;
    TodoListController(
        TodoListService todoListService
    ) {
        this.todoListService = todoListService;
    }


    @JsonView({TodoViews.TodoList.class})
    @GetMapping
    public ResponseEntity<?> all() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return ResponseEntity.ok(todoListService.getAll(username));
    }

	@GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return ResponseEntity.ok(todoListService.getOne(username, id));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<?> items(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return ResponseEntity.ok(
            todoListService.getOne(username, id).getTodoItems()
        );
    }

	@PostMapping
    public ResponseEntity<?> addNew(@RequestBody TodoListRequest newObj) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        long id = todoListService.create(username, newObj).getId();
        return ResponseEntity
            .created(URI.create("" + id))
            .body(Collections.singletonMap("id", id))
        ;
    }

    @PutMapping("/{id}")
    public TodoList replace(
        @PathVariable long id,
        @RequestBody TodoListRequest newObj
    ) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        return todoListService.update(username, id, newObj);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
			.getContext().getAuthentication().getPrincipal()
		;
		String username = userDetails.getUsername();
        todoListService.delete(username, id);
    }

}
