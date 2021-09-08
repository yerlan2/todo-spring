package springj.authenticas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import springj.authenticas.model.audit.DateAudit;


@Entity
@Table(name = "todo_items")
public class TodoItem extends DateAudit {

    @JsonView({TodoViews.TodoItem.class})
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

    @JsonView({TodoViews.TodoItem.class})
    @Column(name = "title", length = 255, nullable = false)
	private String title;

    @JsonView({TodoViews.TodoItem.class})
    @Column(name = "description", columnDefinition="TEXT")
	private String description;

    @JsonView({TodoViews.TodoItem.class})
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    private StatusEnum status;

    @JsonView({TodoViews.TodoItem.class})
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority_id")
    private PriorityEnum priority;

    @JsonView({TodoViews.TodoItem.class})
    @ManyToOne
    @JoinColumn(name = "todo_list_id")
	private TodoList todoList;

    @JsonView({TodoViews.TodoItem.class})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SimpleUser user;


    public TodoItem(){}


    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public PriorityEnum getPriority() {
        return priority;
    }
    public TodoList getTodoList() {
        return todoList;
    }
    public SimpleUser getUser() {
        return user;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }
    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }
    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
