package com.don.shopping.domains.user;

import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
import com.don.shopping.domains.user.service.SignupRequestDto;
import com.don.shopping.domains.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
//    @Mock
//    private CartService cartService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void 회원조회() throws Exception {

        Optional<UserEntity> user = userRepository.findFirstByEmail("TEST@test.com");
        System.out.println(user);
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        SignupRequestDto dto = new SignupRequestDto(
                "TESTMAIL@test.com",
                "TEST",
                "TEST",
                "010", "1234", "5678",
                "012345", "TEST", "TEST");
        Long fakeUserId = 1L;
        UserEntity user = createUser(dto, fakeUserId);
        given(userRepository.findById(any()))
                .willReturn(Optional.ofNullable(user));
        given(userRepository.save(any()))
                .willReturn(user);
//        given(cartService.createCart(any()))
//                .willReturn(any());

        //when
        Long userId = userService.signup(dto);

        //then
        UserEntity findUser = userRepository.findById(userId).get();
        assertEquals(findUser.getEmail(), dto.getEmail());
    }

    private UserEntity createUser(SignupRequestDto dto, Long fakeUserId) {
        UserEntity newMember = dto.toEntity();
        //필드 강제 주입
        ReflectionTestUtils.setField(newMember, "id", fakeUserId);
        return newMember;
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        String duplicatedEmail = "TESTMAIL@test.com";
        SignupRequestDto dto = new SignupRequestDto(
                duplicatedEmail,
                "TEST",
                "TEST",
                "TEST", "TEST", "TEST",
                "TEST", "TEST", "TEST");
        UserEntity user = createUser(dto, 1L);
        given(userRepository.findFirstByEmail(duplicatedEmail))
                .willReturn(Optional.of(user));

        //when, then
        assertThrows(IllegalStateException.class, () -> userService.signup(dto));

    }


}
