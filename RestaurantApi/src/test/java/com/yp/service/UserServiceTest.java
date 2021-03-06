package com.yp.service;

import com.yp.builder.AuthorityBuilder;
import com.yp.builder.AuthorityDtoBuilder;
import com.yp.builder.UserBuilder;
import com.yp.builder.UserDtoBuilder;
import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.CategoryMapper;
import com.yp.mapper.UserMapper;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private MessageSource messageSource;
    @Mock
    private BCryptPasswordEncoder encoder;
    @Mock
    private AuthorityRepository authorityRepository;
    @Mock
    private CategoryMapper categoryMapper;


    private User user = new UserBuilder().withPassWord("123456789").build();
    private UserDto editUserDto = new UserDtoBuilder().build();
    private UserDto userDto = new UserDtoBuilder().build();
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();
    private AuthorityDto authorityDto = new AuthorityDtoBuilder().build();
    private HashSet<AuthorityDto> setDto = new HashSet<>();
    private Authority authority = new AuthorityBuilder().build();
    private HashSet<Authority> set = new HashSet<>();
    private String lang = "en";


    @Before
    public void setUp(){

        set.add(authority);
        setDto.add(authorityDto);
        userList.add(user);
        userDtoList.add(userDto);
        when(userMapper.toDto(any())).thenReturn(userDto);
        when(userMapper.toEntity(any())).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithId(){
        userService.getUser(null, lang);
    }
    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInGetWithNotFound(){
        userService.getUser(2L, lang);
    }

    @Test
    public void shouldgetWithId() {

        UserDto us = userService.getUser(1L, lang);
        assertNotNull(us);
        assertEquals(user.getUsername(), us.getName());
    }

    @Test
    public void shouldgetList(){
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDto> lst = userService.getAllUsers();
        assertEquals(lst.get(0).getName(), userList.get(0).getUsername());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInAddWithId(){
        userService.addUser(null, lang);
    }

    @Test
    public void shouldSaveWithDto() {
        when(userRepository.save(any())).thenReturn(user);
        User us = userService.addUser(userDto, lang);
        assertEquals(us.getUsername(), user.getUsername());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithId(){
        userService.updateUser(null, userDto, lang);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithObject(){
        userService.updateUser(1L, null, lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInEditWithNotFound(){
        userService.updateUser(2L, userDto, lang);
    }

    @Test
    public void shouldEditWithId() {
        User user = new UserBuilder().withId(2L).withUserName("test").withPassWord("test").withAuthorities(new HashSet<Authority>()).withEnabled(false).build();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        User us = userService.updateUser(1L, editUserDto, lang);
        assertEquals(us.getPassword(), user.getPassword());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInDeleteWithId(){
        userService.deleteUser(null, lang);
    }

    @Test
    public void shouldDeleteWithId() {
        userService.deleteUser(1L, lang);
        verify(userRepository, times(1)).deleteById(any());

    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionInGetRoleUserWithNotFound(){
        userService.getUsersWithRole(2L, lang);
    }

    @Test
    public void shouldgetUsersWithId(){
        Mockito.when(authorityRepository.findById(any())).thenReturn(Optional.of(new AuthorityBuilder().build()));
        Set<UserDto> userDtoSet =  userService.getUsersWithRole(1L, lang);
        assertNotNull(userDtoSet);
    }


}