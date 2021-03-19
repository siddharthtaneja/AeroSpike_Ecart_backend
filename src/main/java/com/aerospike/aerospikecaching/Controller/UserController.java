package com.aerospike.aerospikecaching.Controller;

import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/checkuser")
    public String checkLogin(Principal principal){
        System.out.println("Logging in user"+principal.getName());
        return "\"login successfull\"";
    }
    @GetMapping("/getuser")
    public List<Users> getSuccess() {
        return userRepository.findAll();
    }
    @GetMapping("/validate")
    public String valUser(){
        return "Success";
    }
    @GetMapping("/logout")
    @CacheEvict(value = "test",key = "76")
    public String logout(HttpServletRequest req, HttpServletResponse ros) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("LogOut Servlet :"+authentication);

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(req,ros,authentication);
            req.getSession().invalidate();
        }
        return "\"logout successful\"";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody Users  users) {
        users.setActive(1);
        userRepository.save(users);
        return "\"Success\"";
    }
    @PutMapping("update")
    @CachePut(value = "test",key = "76")
    public Users update(@RequestBody Users users){
        users.setActive(1);
        users.setAuthentication("basic");
        userRepository.save(users);
        return users;
    }

}
