package springj.authenticas.payload;

import springj.authenticas.model.PriorityEnum;

public class TodoItemRequest {

    private String title;
    private String description;
    private PriorityEnum priority;
    private String todoList;


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public PriorityEnum getPriority() {
        return priority;
    }
    public String getTodoList() {
        return todoList;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }
    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }

}
