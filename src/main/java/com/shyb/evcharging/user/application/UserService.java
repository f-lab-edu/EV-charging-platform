package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserModifyRequestDto;
import com.shyb.evcharging.user.dto.UserRequestDto;
import com.shyb.evcharging.user.dto.UserResponseDto;
import com.shyb.evcharging.user.exception.EmailDuplicateException;
import com.shyb.evcharging.user.exception.PasswordMisMatchException;
import com.shyb.evcharging.user.exception.UserNotFoundException;
import com.shyb.evcharging.user.repository.UserRepository;
import com.shyb.evcharging.utils.CustomStringUtils;
import com.shyb.evcharging.utils.PasswordEncoder;
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
    public UserResponseDto save(UserRequestDto user) {

        checkEmailDuplicate(user.getEmail());

        boolean sameString = CustomStringUtils.isSameString(user.getPassword(), user.getConfirmPassword());
        if (!sameString) {
            throw new PasswordMisMatchException();
        }

        String encryptedPassword = PasswordEncoder.encrypt(user.getPassword());

        User savedUser = userRepository.save(user.toEntity(encryptedPassword));

        return UserResponseDto.builder()
            .id(savedUser.getId())
            .name(savedUser.getName())
            .phone(savedUser.getPhone())
            .email(savedUser.getEmail())
            .build();
    }

    /**
     * 가입 요청된 이메일 중복 여부를 확인합니다.
     * 만약 동일하지 않다면 예외를 던집니다.
     *
     * @param email 가입 요청된 이메일
     * @return 가입 가능한 이메일 주소인 경우 true를 리턴합니다.
     */
    public void checkEmailDuplicate(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new EmailDuplicateException();
        }
    }

    /**
     * 사용자를 조회합니다.
     *
     * @param id 사용자 식별자
     * @return 조회된 사용자
     */
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .phone(user.getPhone())
            .email(user.getEmail())
            .build();
    }

    /**
     * 사용자 정보가 있으면 정보를 업데이트 합니다.
     *
     * @param id 사용자 식별자 id
     * @param userModifyRequestDto 사용자 정보 요청 DTO
     * @return 정보가 변경된 사용자
     */
    public UserResponseDto update(Long id, UserModifyRequestDto userModifyRequestDto) {

        String encryptPassword = null;
        if (userModifyRequestDto.getPassword() != null && userModifyRequestDto.getConfirmPassword() != null) {
            boolean sameString = CustomStringUtils.isSameString(userModifyRequestDto.getPassword(), userModifyRequestDto.getConfirmPassword());
            if (!sameString) {
                throw new PasswordMisMatchException();
            }
            encryptPassword = PasswordEncoder.encrypt(userModifyRequestDto.getPassword());
        }

        User updateUser = User.builder()
            .name(userModifyRequestDto.getName())
            .password(encryptPassword)
            .phone(userModifyRequestDto.getPhone())
            .build();

        if (findById(id) != null) {
            userRepository.update(id, updateUser);
        }

        return findById(id);
    }
}
