package jjfactory.shook.busniess.wish.repository;

import jjfactory.shook.busniess.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish,Long> {
}
