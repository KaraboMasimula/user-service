package za.co.reverside.training.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter @Setter
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "married")
	private boolean married;

	@Column(name = "dob")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dom")
	private String dateOfMarriage;

	@Column(name = "photo")
	private String photo;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	@ManyToOne
	private Role role;

	public User() {
	}

	public User(String firstName, String lastName, String dateOfBirth, String gender, boolean married,
			String dateOfMarriage, String photo, String email, String password 
			,Role role
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.dateOfMarriage = dateOfMarriage;
		this.photo = photo;
		this.email = email;
		this.password = password;
		this.married = married;
		this.role = role;
	}

	
}
