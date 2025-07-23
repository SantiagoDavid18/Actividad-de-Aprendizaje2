package ec.edu.espe.openai.Controller;

import ec.edu.espe.openai.Entity.Activity;
import ec.edu.espe.openai.Dto.PromptRequest;
import ec.edu.espe.openai.Service.OpenAIService;
import ec.edu.espe.openai.Service.TextToSpeechService;
import ec.edu.espe.openai.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final OpenAIService openAIService;
    private final TextToSpeechService textToSpeechService;
    private final ActivityService activityService;

    @PostMapping("/generate/{childProfileId}")
    public ResponseEntity<?> generateActivity(
            @PathVariable Long childProfileId,
            @Valid @RequestBody PromptRequest request) {

        // Generar texto con OpenAI
        String generatedText = openAIService.generateActivity(request.getMessage());

        // Generar audio
        String audioFileName = "audio_" + System.currentTimeMillis() + ".mp3";
        String audioPath = textToSpeechService.synthesizeText(generatedText, audioFileName);

        // Construir URL pública
        String audioUrl = "http://localhost:8080/audio/" + audioFileName;

        // Guardar en BD
        Activity savedActivity = activityService.generateAndSaveActivity(childProfileId, request.getMessage(), audioUrl, generatedText);

        return ResponseEntity.ok(savedActivity);
    }

    @GetMapping
    public ResponseEntity<?> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<List<Activity>> getActivitiesByChild(@PathVariable Long childId) {
        return ResponseEntity.ok(activityService.getActivitiesByChild(childId));
    }

    @GetMapping("/progress/{childId}")
    public ResponseEntity<?> getProgress(@PathVariable Long childId) {
        return ResponseEntity.ok(activityService.getProgressByChild(childId));
    }

}

