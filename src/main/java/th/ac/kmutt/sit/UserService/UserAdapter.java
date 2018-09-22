package th.ac.kmutt.sit.UserService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.kmutt.sit.UserService.User;

import java.util.List;

@Service
public class UserAdapter {

    public User getUserDetail(long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/user/" + userId;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    public List<User> getAllUser() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/users";
        List<User> users = restTemplate.getForEntity(url, List.class).getBody();
        return users;
    }

}
