package ec.edu.espe.openai.Controller;

import ec.edu.espe.openai.Entity.ChildProfile;
import ec.edu.espe.openai.Service.ChildProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/child-profiles")
@CrossOrigin(origins = "http://localhost:5173")
public class ChildProfileController {

    private final ChildProfileService childProfileService;

    public ChildProfileController(ChildProfileService childProfileService) {
        this.childProfileService = childProfileService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ChildProfile> createProfile(@PathVariable Long userId, @RequestBody ChildProfile profile) {
        return ResponseEntity.ok(childProfileService.createProfile(profile, userId));
    }

    @GetMapping
    public ResponseEntity<List<ChildProfile>> getAllProfiles() {
        return ResponseEntity.ok(childProfileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildProfile> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(childProfileService.getProfileById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildProfile> updateProfile(@PathVariable Long id, @RequestBody ChildProfile profileDetails) {
        return ResponseEntity.ok(childProfileService.updateProfile(id, profileDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        childProfileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}

