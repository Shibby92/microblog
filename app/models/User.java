package models;

import javax.persistence.*;

import helpers.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.db.ebean.Model;

@Entity
public class User extends Model {
	
	@Id
	public long id;
	
	@Email
	public String email;
	
	@MinLength(6)
	public String password;
	
	public User(){
		this.email=null;
		this.id=-1;
		this.password=null;
	}
	
	public User(String email, String password ){
		this.email=email;
		this.password=HashHelper.createPassword(password);
	}
	static Finder<Long,User> find= new Finder<Long,User>(Long.class,User.class);
	
	public static long createUser (String email, String password){
		User newUser= new User(email,password);
		newUser.save();
		return newUser.id;
	}
	public static User find(long id){
		return find.byId(id);
	}
public static User find(String email){
	return find.where().eq("email", email).findUnique();
}

}
