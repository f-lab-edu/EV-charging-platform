package com.shyb.evcharging.user.api;

import com.shyb.evcharging.user.application.UserService;
import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.EmailDuplicateCheckRequestDto;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 생성, 조회, 수정, 삭제에 대한 요청 및 응답을 처리합니다.
 */

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자를 등록한 후 리턴합니다.
     *
     * @param user 생성 요청 사용자 정보
     * @return 등록된 사용자
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto save(@RequestBody @Valid UserRequestDto user) {
        return userService.save(user);
    }

    /**
     * 이메일 주소로 사용자 중복여부를 확인합니다.
     *
     * @param email 이메일 중복 요청 DTO
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/email/check")
    public boolean checkEmailDuplicate(@RequestBody @Valid EmailDuplicateCheckRequestDto email) {
        return userService.checkEmailDuplicate(email);
    }

    /**
     * 사용자 정보를 업데이트 합니다.
     *
     * @param id 사용자 식별자
     * @param userModifyRequestDto 사용자 정보 요청 DTO
     * @return 정보 변경된 사용자 정보
     */
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody @Valid UserModifyRequestDto userModifyRequestDto) {
        return userService.update(id, userModifyRequestDto);
    }


    /**
     * 사용자를 조회합니다.
     *
     * @param id 조회할 사용자 식별자
     * @return 조회된 사용자
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserResponseDto find(@PathVariable("id") long id) {
        return userService.findById(id);
    }

}
