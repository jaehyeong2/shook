package jjfactory.shook.busniess.repository.wish;

import jjfactory.shook.busniess.domain.wish.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish,Long> {
}
