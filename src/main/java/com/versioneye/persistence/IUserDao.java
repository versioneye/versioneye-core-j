package com.versioneye.persistence;

import com.versioneye.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/13/12
 * Time: 5:38 PM
 *
 */
public interface IUserDao {

    User create(User user);

    User getById(String id);

    User getByUsername(String username);

}
