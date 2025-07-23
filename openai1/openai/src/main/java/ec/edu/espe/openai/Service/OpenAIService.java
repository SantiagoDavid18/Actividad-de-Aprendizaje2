package ec.edu.espe.openai.Service;

import ec.edu.espe.openai.Entity.Activity;
import ec.edu.espe.openai.Repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final OpenAiChatModel openAiChatModel;
    private final ActivityRepository activityRepository;

    public String generateActivity(String message) {
    return "Respuesta simulada para: " + message;
   }
//public String generateActivity(String message) {
  // Prompt prompt = new Prompt(message);
//return openAiChatModel.call(prompt).getResult().getOutput().getContent();
//}

    public void saveActivity(String prompt, String response) {
        Activity activity = new Activity();
        activity.setPrompt(prompt);
        activity.setResponse(response);
        activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}