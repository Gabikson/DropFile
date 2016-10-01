package com.gabiksoft.webapp.engine.user.service;

import com.gabiksoft.webapp.engine.user.entity.User;
import com.gabiksoft.webapp.exceptions.UserNotAuthenticated;
import com.gabiksoft.webapp.engine.custom.service.GenericService;

public interface UserService extends GenericService<User> {

    boolean userWithNameExists(String login);

    User getCurrentUser() throws UserNotAuthenticated;
}
