package com.noom.interview.fullstack.sleep.user;

import com.noom.interview.fullstack.sleep.user.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
