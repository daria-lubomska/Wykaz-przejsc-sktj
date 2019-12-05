package sktj.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sktj.parser.entity.User;

@Repository
public interface UserRepositoryBasic extends JpaRepository<User, Long> {

}
