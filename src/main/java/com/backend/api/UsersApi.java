package com.backend.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UsersApi {

    @RequestMapping(path = "/signin", method = POST)
    public void signIn(){

        System.out.println("signin controller");
    }

    @RequestMapping(path = "/signout", method = POST)
    public void signOut(){

        System.out.println("signout controller");
    }

    @RequestMapping(path = "/signup", method = POST)
    public void signUp(){

        System.out.println("signup controller");
    }

    @RequestMapping(path = "/userInfo", method = POST)
    public void userInfo(){

        System.out.println("userInfo controller");
    }

}
