package controllers;

import helpers.HashHelper;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	public static class Login {
		public String email;
		public String password;

		public String validate() {
			User u = User.find(email);
			if (u == null) {
				return "Netacan email ili password";
			}
			if (HashHelper.checkPassword(password, u.password) == false) {
				return "Netacan email ili password";

			}
			return null;
		}

	}

	static Form<Login> loginForm = new Form<Login>(Login.class);

	public static Result index() {
		return ok(index.render(loginForm));
	}

	public static Result signIn() {
		Form<Login> submit = loginForm.bindFromRequest();
		if (submit.hasGlobalErrors()) {
			return ok(index.render(submit));
		}
		String email = submit.get().email;
		String password = submit.get().password;
		User u = User.find(email);
		if (u == null) {
			return ok(index.render(submit));
		}
		if (HashHelper.checkPassword(password, u.password)) {
			session("user_id", Long.toString(u.id));
			redirect("/user/" + u.id);
		} else {
			return ok(index.render(submit));
		}
		return TODO;
	}

}
