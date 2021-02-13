package com.toan.chatdemo.services.impls;

import com.toan.chatdemo.daos.UserDao;
import com.toan.chatdemo.daos.CustomDao;
import com.toan.chatdemo.entities.User;
import com.toan.chatdemo.exceptions.InternalServerException;
import com.toan.chatdemo.models.UserPrincipal;
import com.toan.chatdemo.models.dtos.AddUserDto;
import com.toan.chatdemo.models.dtos.ViewUserDto;
import com.toan.chatdemo.models.search.GridParam;
import com.toan.chatdemo.services.UserService;
import com.toan.chatdemo.utils.FileUtils;
import com.toan.chatdemo.utils.PasswordGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findOne(Specification
                .where((root, query, cb) -> cb.equal(root.get("username"), username)))
                .orElseThrow(() -> new UsernameNotFoundException("Not found user with username: " + username));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserPrincipal principal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getEnabled(), true,
                true, true, authorities);
        principal.setId(user.getId());
        principal.setName(user.getName());
        principal.setImage(user.getImage());

        return principal;
    }

    @Override
    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
        User user = userDao.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Not found user with id: " + userId));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserPrincipal principal = new UserPrincipal(user.getUsername(), user.getPassword(), user.getEnabled(), true,
                true, true, authorities);
        principal.setId(user.getId());
        principal.setName(user.getName());
        principal.setImage(user.getImage());

        return principal;
    }

    @Override
    public void add(AddUserDto dto) throws InternalServerException {
        User old = userDao.findOne(Specification
                .where((root, query, cb) -> cb.equal(root.get("username"), dto.getUsername())))
                .orElse(null);
        if (old == null) {
            User user = modelMapper.map(dto, User.class);
            user.setId(null);
            user.setPassword(PasswordGenerator.getHashString(user.getPassword()));
            user.setEnabled(true);
            MultipartFile file = dto.getFile();
            if (file != null && file.getSize() > 0) {
                user.setImage(FileUtils.saveFileByTime(dto.getFile()));
            } else {
                user.setImage("/avatar.jpg");
            }
            userDao.save(user);
            dto.setId(user.getId());
        } else {
            throw new InternalServerException("Username already exists");
        }
    }

    @Override
    public ViewUserDto getById(Long id) throws InternalServerException {
        User user = userDao.findById(id).orElseThrow(() -> new InternalServerException("Not found user with id: " + id));
        ViewUserDto dto = modelMapper.map(user, ViewUserDto.class);
        return dto;
    }

    @Override
    public List<ViewUserDto> searchWithPaging(GridParam param) {
        // List<User> users = userDao.findAll(CustomDao.getGridResult(null, param));
        return null;
    }
}
