package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author cf
 * @date 2020/8/29
 */
public interface UserFactory {

    default User createUser() {
        return User.create();
    }

    default User createUser(Long id, String name) {
        return User.create(id, name);
    }
}
