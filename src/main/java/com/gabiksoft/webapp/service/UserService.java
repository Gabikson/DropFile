package com.gabiksoft.webapp.service;

import com.gabiksoft.webapp.entity.User;

public interface UserService extends GenericService<User>{

    boolean userWithNameExists(String login);
}
