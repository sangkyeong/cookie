package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorizationCookie",required = false)
            String authorizationCookie
    ){

        log.info("authorizationCookie : {}", authorizationCookie);

        var OptionalUserDto = userRepository.findById(authorizationCookie);
//        var cookie = httpServletRequest.getCookies();
//        if(cookie!= null){
//            for(Cookie cookie1 : cookie){
//                log.info("key : {}, value : {}", cookie1.getName(), cookie1.getValue());
//            }
//        }
     return OptionalUserDto.get();
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization", required = false)
            String authorizationHeader
    ){
        log.info("authorizationCookie : {}", authorizationHeader);
        var OptionalUserDto = userRepository.findById(authorizationHeader);
        return OptionalUserDto.get();
    }
}
