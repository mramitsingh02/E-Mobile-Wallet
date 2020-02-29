package com.tester.spring.rest.webservices.services;

import com.tester.spring.rest.webservices.repository.pojo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserRepositoryServices {


    private static Map<Long, User> userMap = new HashMap<>();
    private static AtomicLong idGenerator = new AtomicLong(10000);
    ;

    static {
        User user1 = new User(idGenerator.incrementAndGet(), "Amit Kumar Singh", "mramitsingh02@gmail.com", new Date());
        User user2 = new User(idGenerator.incrementAndGet(), "Arpit Kumar Singh", "mrarpitsingh02@gmail.com", new Date());
        User user3 = new User(idGenerator.incrementAndGet(), "Sivam Kumar Singh", "mrsivamsingh02@gmail.com", new Date());
        User user4 = new User(idGenerator.incrementAndGet(), "Ashish Kumar Singh", "mrashishsingh02@gmail.com", new Date());
        userMap.put(user1.getUserId(), user1);
        userMap.put(user2.getUserId(), user2);
        userMap.put(user3.getUserId(), user3);
        userMap.put(user4.getUserId(), user4);
    }


    public List<User> listUsers() {
        return new ArrayList<User>(userMap.values());
    }

    public User loadUserByUserId(Long userId) {
        return userMap.get(userId);
    }

    public User saveUser(User user) {
        if (user == null) {
            return null;
        }
        if (user.getUserId() == null) {
            user.setUserId(idGenerator.incrementAndGet());
        }
        userMap.put(user.getUserId(), user);
        return user;
    }

    public User updateUser(User user) {
        if (user == null || user.getUserId() == null) {
            return null;
        }
        userMap.put(user.getUserId(), user);
        return user;
    }

    public User deleteUser(User user) {
        if (user == null || user.getUserId() == null) {
            return null;
        }
        return userMap.remove(user.getUserId());
    }


}
