package springj.authenticas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class SimpleUser {

    @JsonView({TodoViews.TodoItem.class})
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;


    @JsonView({TodoViews.TodoItem.class})
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;


	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}