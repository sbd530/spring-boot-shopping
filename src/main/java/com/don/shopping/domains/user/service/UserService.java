package com.don.shopping.domains.user.service;

import com.don.shopping.domains.cart.service.CartService;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
import com.don.shopping.domains.user.query.dao.UserDao;
import com.don.shopping.domains.user.query.dto.ChangePasswordDto;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService  {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    /**
     * 이메일 중복 검사 후 회원 가입
     * @param dto 입력값 객체
     * @return 생성된 아이디 Or throw IllegalStateException
     */
    public Long signup(SignupRequestDto dto) {
        //이메일 중복 검사
        validateDuplicateUser(dto.getEmail());
        //새 회원 생성
        UserEntity newUser = dto.toEntity();
        //회원 저장
        Long userId = userRepository.save(newUser).getId();
        //새 회원 장바구니 생성
        cartService.createCart(userId);
        return userId;
    }
    public void validateDuplicateUser(String email) {
        if (userRepository.findFirstByEmail(email).isPresent())
            throw new IllegalStateException("이미 회원이 존재합니다.");
    }

    //유저 정보를 dto 에 담는다
    public MyPageResponseDto getUserInfo(String email) {
        UserEntity userEntity = validateExistAndGetUser(email);
        return new MyPageResponseDto(userEntity);
    }
    public UserEntity validateExistAndGetUser(String email) {
        Optional<UserEntity> user = userRepository.findFirstByEmail(email);
        return user.
                orElseThrow(() ->new IllegalStateException("회원이 존재하지 않습니다."));
    }

    public boolean isExistEmail(String email) {
        Optional<UserEntity> user = userRepository.findFirstByEmail(email);
        return user.isPresent();
    }

    //유저 정보 수정
    public void updateUserInfo(String email, MyPageRequestDto dto) {
        userDao.updateUserInfo(email, dto);
    }

    //유저 삭제
    public boolean deleteUser(String email, String password) {
        Optional<UserEntity> user = userRepository.findFirstByEmail(email);
        if (user.isPresent()) {
            String dbPassword = user.get().getPassword();
            if (encoder.matches(password, dbPassword)) {
                cartService.deleteCart(user.get().getId());// 카트 삭제
                userRepository.delete(user.get()); // 유저 삭제
                return true;
            }
        }
        return false;
    }

    //비번 변경
    public boolean modifyPassword(String email, ChangePasswordDto dto) {
        UserEntity userEntity = validateExistAndGetUser(email);
        if(!encoder.matches(dto.getPresentPassword(),userEntity.getPassword()))
            return false;
        userDao.modifyPassword(email, encoder.encode(dto.getNewPassword()));
        return true;
    }

    //비번 초기화
    public void initializePassword(String email, String newPassword) {
        validateExistAndGetUser(email);
        userDao.modifyPassword(email, encoder.encode(newPassword));
    }

    //이메일로 아이디 조회
    public Long findUserIdByEmail(String email) {
        UserEntity userEntity = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));
        return userEntity.getId();
    }

    // userId 로 유저 엔티티 조회
    public UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 아이디입니다."));
    }

    //로그인 정보 검증
    public boolean validateLoginData(LoginRequestDto dto) {
        Optional<UserEntity> checkUser = userRepository.findFirstByEmail(dto.getEmail());
        if (checkUser.isPresent()) {
            String dbPassword = checkUser.get().getPassword();
            return encoder.matches(dto.getPassword(), dbPassword);
        }
        return false;
    }

}
