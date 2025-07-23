package ec.edu.espe.openai.Service;

import ec.edu.espe.openai.Entity.ChildProfile;
import ec.edu.espe.openai.Entity.User;
import ec.edu.espe.openai.Repository.ChildProfileRepository;
import ec.edu.espe.openai.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildProfileService {

    private final ChildProfileRepository childProfileRepository;
    private final UserRepository userRepository;

    public ChildProfileService(ChildProfileRepository childProfileRepository, UserRepository userRepository) {
        this.childProfileRepository = childProfileRepository;
        this.userRepository = userRepository;
    }

    public ChildProfile createProfile(ChildProfile childProfile, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        childProfile.setParentOrTherapist(user);
        return childProfileRepository.save(childProfile);
    }

    public List<ChildProfile> getAllProfiles() {
        return childProfileRepository.findAll();
    }

    public ChildProfile getProfileById(Long id) {
        return childProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
    }

    public ChildProfile updateProfile(Long id, ChildProfile profileDetails) {
        ChildProfile profile = getProfileById(id);
        profile.setName(profileDetails.getName());
        profile.setAge(profileDetails.getAge());
        profile.setDiagnosis(profileDetails.getDiagnosis());
        profile.setProgress(profileDetails.getProgress());
        return childProfileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        childProfileRepository.deleteById(id);
    }
}

