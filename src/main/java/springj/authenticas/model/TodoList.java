package springj.authenticas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import springj.authenticas.model.audit.DateAudit;


@Entity
@Table(name = "todo_lists", uniqueConstraints={
    @UniqueConstraint(columnNames = {"name", "user_id"})
})
public class TodoList extends DateAudit {

	@JsonView({TodoViews.TodoList.class, TodoViews.TodoItem.class})
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@JsonView({TodoViews.TodoList.class, TodoViews.TodoItem.class})
    @Column(name = "name", length = 255, nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(
		mappedBy = "todoList",
		cascade = CascadeType.ALL
	)
	private List<TodoItem> todoItems;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private SimpleUser user;


	public TodoList() {}


	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<TodoItem> getTodoItems() {
		return todoItems;
	}
	public SimpleUser getUser() {
		return user;
	}


	public void setName(String name) {
		this.name = name;
	}
	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}
	public void setUser(SimpleUser user) {
		this.user = user;
	}

}