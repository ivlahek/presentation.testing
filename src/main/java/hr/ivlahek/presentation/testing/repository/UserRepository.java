package hr.ivlahek.presentation.testing.repository;

import hr.ivlahek.presentation.testing.entity.User;

import javax.ejb.Stateful;
import javax.transaction.Transactional;

/**
 * Created by ivlahek on 23.6.2016..
 */
@Transactional
@Stateful
public class UserRepository extends AbstractRepository<User> {
    @Override
    protected Class<User> returnEntityClass() {
        return User.class;
    }
}
