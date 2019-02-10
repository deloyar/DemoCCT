package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.LogUser;
import com.example.auth.TokenResponseEntity;
import com.example.service.WebToken;

@RestController
@RequestMapping(path = "/token")
public class TokenGeneratorController {

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<TokenResponseEntity> generate(HttpServletRequest httpRequest, @RequestBody LogUser user) {
        
        WebToken WebToken = new WebToken();
        
        if(user.getName().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("admin")){
            String token = WebToken.CreateToken(user.getName(), user.getPassword());
            TokenResponseEntity tokenResponse = new TokenResponseEntity(token, user.getName());
            tokenResponse.logStatus="1";
            return new ResponseEntity<TokenResponseEntity>(tokenResponse, HttpStatus.OK);
        }
        return new ResponseEntity<TokenResponseEntity>(new TokenResponseEntity(), HttpStatus.OK);
    }


    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String verify() {
        return "Spring boot is working!";
    }
    
    @RequestMapping(value = "/testToken", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String testingToken(HttpServletRequest httpRequest) throws Exception {
        
        try {
            String token = httpRequest.getHeader("token");
            WebToken WebToken = new WebToken();
            WebToken.VerifyToken(token);
            
            String userId = WebToken.GetPropertyFromToken(token, "UserId");
            String password = WebToken.GetPropertyFromToken(token, "Password");
            
            
            if(userId.equalsIgnoreCase("admin") && password.equalsIgnoreCase(password)){
                return "authenticated";
            }
            
            return "not authenticated";
        } catch (Exception ex) {
            throw ex;
        }
    }
}
