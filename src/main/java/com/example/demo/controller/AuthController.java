package com.example.demo.controller;

import com.example.demo.config.RequestFilter;
import com.example.demo.model.Jwt;
import com.example.demo.model.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    Logger logger = LogManager.getLogger(AuthController.class);

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestHeader(value = "msgId") String msgId,
                                               @RequestBody LoginRequest loginRequest) throws Exception {

        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setMsgId(loginRequest.getMsgId());
        authenticate(loginRequest.getUserName(), loginRequest.getUserPassword());

//        final User userDetails = userService.loadUserByUsername(loginRequest.getUserName());
        final String token = jwtTokenUtil.generateToken(loginRequest.getUserName());
        Jwt jwt = new Jwt();
        jwt.setToken(token);
        loginResponse.setJwt(jwt);
        logger.info("Finish");
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
