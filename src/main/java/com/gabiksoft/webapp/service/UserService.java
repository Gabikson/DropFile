package com.gabiksoft.webapp.service;

import com.gabiksoft.webapp.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;

public interface UserService extends GenericService<User>{

    boolean userWithNameExists(String login);

    User getCurrentUser() throws UserNotAuthenticated;
}
