package ec.edu.espe.openai.Service;

import ec.edu.espe.openai.Entity.Activity;
import ec.edu.espe.openai.Entity.ChildProfile;
import ec.edu.espe.openai.Repository.ActivityRepository;
import ec.edu.espe.openai.Repository.ChildProfileRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ChildProfileRepository childProfileRepository;

    public ActivityService(ActivityRepository activityRepository, ChildProfileRepository childProfileRepository) {
        this.activityRepository = activityRepository;
        this.childProfileRepository = childProfileRepository;
    }

    public Activity saveActivity(String prompt, String response, String audioUrl, Long childId) {
        ChildProfile child = childProfileRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child profile not found"));

        Activity activity = new Activity();
        activity.setPrompt(prompt);
        activity.setResponse(response);
        activity.setAudioUrl(audioUrl);
        activity.setChildProfile(child);

        return activityRepository.save(activity);
    }

    public Activity generateAndSaveActivity(Long childProfileId, String message, String audioUrl, String response) {
        Activity activity = new Activity();
        activity.setPrompt(message);
        activity.setResponse(response);
        activity.setAudioUrl(audioUrl);

        ChildProfile child = childProfileRepository.findById(childProfileId)
                .orElseThrow(() -> new RuntimeException("Child profile not found"));
        activity.setChildProfile(child);

        return activityRepository.save(activity);
    }

    public int countActivitiesByChild(Long childId) {
        return activityRepository.countByChildProfileId(childId);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByChild(Long childId) {
        return activityRepository.findByChildProfileId(childId);
    }

    // Progreso con últimas 3 actividades
    public Map<String, Object> getProgressByChild(Long childId) {
        long totalActivities = activityRepository.countByChildProfileId(childId);

        Pageable topThree = PageRequest.of(0, 3);
        List<Activity> recentActivities = activityRepository.findLastActivitiesByChild(childId, topThree);

        // Cálculo de porcentaje opcional
        int goal = 20;
        int progressPercent = (int) ((totalActivities * 100) / goal);

        Map<String, Object> response = new HashMap<>();
        response.put("childId", childId);
        response.put("totalActivities", totalActivities);
        response.put("progressPercent", progressPercent);
        response.put("recentActivities", recentActivities);

        return response;
    }
}


