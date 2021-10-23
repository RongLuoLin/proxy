package org.shadow.service.Impl;

import org.shadow.service.UserService;

/**
 *
 */
public class UserServiceImpl implements UserService {
    @Override
    public String query(int id, String name) {
        System.out.println("id:" + id);
        System.out.println("query database ------  logic");
        return name;
    }

    @Override
    public void del() {
        System.out.println("del mysql ----- logic");
    }
}
