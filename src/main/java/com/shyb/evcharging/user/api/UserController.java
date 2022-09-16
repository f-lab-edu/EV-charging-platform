package com.shyb.evcharging.user.api;

import com.shyb.evcharging.user.application.UserService;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserRequestDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 생성, 조회, 수정, 삭제에 대한 요청 및 응답을 처리합니다.
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자를 등록한 후 리턴합니다.
     *
     * @param user 생성 요청 사용자 정보
     * @return 등록된 사용자
     */
    @PostMapping
    public User save(@RequestBody @Valid UserRequestDto user) {
        return userService.save(user);
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param id 조회할 사용자 식별자
     * @return 조회된 사용자
     */
    @GetMapping("/users/{id}")
    public String find(@PathVariable("id") long id) {
        if (userService.findById(id).isPresent()) {
            return "find user";
        } else {
            return "no user";
        }
    }

}
