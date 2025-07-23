package ec.edu.espe.openai.Controller;

import ec.edu.espe.openai.Repository.ActivityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/child-profiles")
@CrossOrigin(origins = "http://localhost:5173")
public class ChildProgressController {

    private final ActivityRepository activityRepository;

    public ChildProgressController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/{childId}/progress")
    public ResponseEntity<Map<String, Object>> getChildProgress(@PathVariable Long childId) {
        long totalActivities = activityRepository.countActivitiesByChild(childId);

        return ResponseEntity.ok(Map.of(
                "childId", childId,
                "totalActivities", totalActivities
        ));
    }
}

