package com.toan.chatdemo.services;

import com.toan.chatdemo.exceptions.InternalServerException;
import com.toan.chatdemo.models.dtos.AddUserDto;
import com.toan.chatdemo.models.dtos.ViewUserDto;
import com.toan.chatdemo.models.search.GridParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException;

    public void add(AddUserDto dto) throws InternalServerException;

//    public void update(UpdateUserDto dto) throws InternalServerException;

//    public void delete(Long id) throws InternalServerException;

    public ViewUserDto getById(Long id) throws InternalServerException;

    public List<ViewUserDto> searchWithPaging(GridParam param);

//    public Long getTotalRecord(UserSearch search);
}
