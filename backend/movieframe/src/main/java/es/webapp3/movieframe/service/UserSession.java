package es.webapp3.movieframe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import es.webapp3.movieframe.model.User;

@Component
@SessionScope
public class UserSession {

    @Autowired
    private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
    
}
