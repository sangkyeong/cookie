package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public String login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optinalUser = userRepository.findByName(id);

        if(optinalUser.isPresent()){
            var userDto = optinalUser.get();

            if(userDto.getPassword().equals(pw)){
               /* //쿠키 저장(토큰 or JWT토큰)
                var cookie = new Cookie("authorizationCookie", userDto.getId());    //쿠키이름, 쿠키에 저장할 정보
                cookie.setDomain("localhost");  //구글이나 네이버인경우 해당 도메인을 입력((www.>생략)naver.com)
                //해당 도메인에서만 쿠키 사용가능
                cookie.setPath("/");    //저장 경로 지정
                cookie.setHttpOnly(true);   //http환경에서만 쿠키전송
                cookie.setSecure(true); //https환경에서만 쿠키전송
                //>>javaScript에서 쿠키값 변조, 접근 불가
                cookie.setMaxAge(-1);   //-1 창 닫힐때까지 or 시간지정가능

                httpServletResponse.addCookie(cookie);*/

                return userDto.getId();
            }
        }else{
            throw  new RuntimeException("USER NOT FOUND");
        }
        return null;
    }
}
