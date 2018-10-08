package za.co.reverside.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="role")
@Getter @Setter
public class Role implements Serializable{
	
	private static final long serialVersionUID = 890245234L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name")
	private String name;
		
	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
	
}
