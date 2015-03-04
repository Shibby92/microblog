import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {
	@Override
	public void onStart(Application app){
		User.createUser("test◊mail.com", "123456");
	}

}
