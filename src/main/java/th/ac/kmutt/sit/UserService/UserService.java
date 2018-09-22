package th.ac.kmutt.sit.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(long userId) {
        return this.userRepository.findById(userId);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
