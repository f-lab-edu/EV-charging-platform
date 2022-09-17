package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.EmailDuplicateCheckRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.exception.PasswordMisMatchException;
import com.shyb.evcharging.user.repository.UserRepository;
import com.shyb.evcharging.user.repository.UserSearchCond;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자를 등록한 후 리턴합니다.
     *
     * @param user 생성 요청 사용자 정보
     * @eturn 등록된 사용자
     */
    public User save(UserRequestDto user) {
        return userRepository.save(user.toEntity());
    }

    /**
     * 가입 요청된 이메일 중복 여부를 확인합니다.
     * 만약 동일하지 않다면 예외를 던집니다.
     *
     * @param emailDuplicateCheckRequestDto 가입 요청된 이메일 DTO
     * @return 가입 가능한 이메일 주소인 경우 true를 리턴합니다.
     */
    public boolean checkEmailDuplicate(EmailDuplicateCheckRequestDto emailDuplicateCheckRequestDto) {
        String email = emailDuplicateCheckRequestDto.getEmail();
        Optional<UserResponseDto> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new EmailDuplicateException();
        }
        return true;
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param id 사용자 식별자
     * @return 조회된 사용자
     */
    public Optional<UserResponseDto> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserResponseDto> findAll(UserSearchCond userSearch) {
        return userRepository.findAll(userSearch);
    }
}
