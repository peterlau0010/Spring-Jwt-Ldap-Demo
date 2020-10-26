package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.response.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LogManager.getLogger(UserController.class);

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@RequestHeader(value = "msgId") String msgId) {
        UserResponse userResponse = new UserResponse();
        User user = new User();
        user.setUsername("Success");
        userResponse.setUser(user);
        logger.info("Finish");
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
