package ec.edu.espe.openai.Repository;

import ec.edu.espe.openai.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // Usar @Query personalizada
    @Query("SELECT COUNT(a) FROM Activity a WHERE a.childProfile.id = :childId")
    long countActivitiesByChild(@Param("childId") Long childId);

    // Método derivado
    int countByChildProfileId(Long childId);

    // Listar todas las actividades por niño
    List<Activity> findByChildProfileId(Long childId);

    @Query("SELECT a FROM Activity a WHERE a.childProfile.id = :childId ORDER BY a.createdAt DESC")
    List<Activity> findLastActivitiesByChild(@Param("childId") Long childId, Pageable pageable);

    }




