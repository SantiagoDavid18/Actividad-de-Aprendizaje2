package ec.edu.espe.openai.Repository;

import ec.edu.espe.openai.Entity.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {
}

