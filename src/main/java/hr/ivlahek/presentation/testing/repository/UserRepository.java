package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.User;

/**
 * Created by ivlahek on 23.6.2016..
 */
public class UserRepository extends AbstractRepository<User> {
    @Override
    protected Class<User> returnEntityClass() {
        return User.class;
    }
}
