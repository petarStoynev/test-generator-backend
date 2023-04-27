package testgenerator.bg.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import testgenerator.bg.entity.User;
import testgenerator.bg.entity.dto.ProfileDTO;
import testgenerator.bg.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileDTO getProfileInfo(String email){

        User user = userRepository.findByEmail(email).orElseThrow();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEmail(user.getEmail());
        profileDTO.setFirstname(user.getFirstName());
        profileDTO.setLastname(user.getLastName());

        return profileDTO;
    }

}
