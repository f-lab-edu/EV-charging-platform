package com.shyb.evcharging.user.application;

import com.shyb.evcharging.user.domain.User;
import com.shyb.evcharging.user.dto.UserRequestDto;
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
     * 사용자를 조회합니다.
     *
     * @param id 사용자 식별자
     * @return 조회된 사용자
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll(UserSearchCond userSearch) {
        return userRepository.findAll(userSearch);
    }
}
