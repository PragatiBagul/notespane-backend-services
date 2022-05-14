package notespane.notespanebackendservices.repository;

import notespane.notespanebackendservices.model.Saved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SavedRepository extends JpaRepository<Saved, Long>, JpaSpecificationExecutor<Saved> {

    List<Saved> findAllByUserId(Long id);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}