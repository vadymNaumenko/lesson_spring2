package spring.eventlisner.Repository;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spring.eventlisner.configuration.ConfigBean;
import spring.eventlisner.entity.EventEntity;
import spring.eventlisner.entity.User;

import java.util.ArrayList;

@Component
public class UserRepository {

    private ArrayList<User> pool = new ArrayList();
    @PreDestroy
    public void created(){
        User user = User.builder()
                .userId(7)
                .name("Roma")
                .build();
        pool.add(user);
    }

    public User save(User user){
        pool.add(user);
        return user;
    }
    public User deleteById(int id){
        User findUser = pool.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
        if (findUser != null){
         pool.remove(findUser);
         return findUser;
        }
        return null;
    }
    public User findById(int id){
       return pool.stream()
                .filter(user -> user.getUserId()==id)
               .findFirst().orElse(null);
    }

}
