package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
   public User findByUid(String uid);
}