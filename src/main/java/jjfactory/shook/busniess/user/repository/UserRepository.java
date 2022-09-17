package jjfactory.shook.busniess.user.repository;

import jjfactory.shook.busniess.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
