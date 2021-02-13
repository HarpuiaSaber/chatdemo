package com.toan.chatdemo.apis;

import com.toan.chatdemo.components.jwt.JwtTokenProvider;
import com.toan.chatdemo.exceptions.InternalServerException;
import com.toan.chatdemo.models.JwtResponse;
import com.toan.chatdemo.models.LoginRequest;
import com.toan.chatdemo.models.UserPrincipal;
import com.toan.chatdemo.models.dtos.AddUserDto;
import com.toan.chatdemo.models.dtos.ViewUserDto;
import com.toan.chatdemo.models.search.GridParam;
import com.toan.chatdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = -1)
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/test")
    @ResponseBody
    public String test() {
        return "tested";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    @ResponseBody
    public AddUserDto create(@ModelAttribute AddUserDto dto) throws InternalServerException {
        userService.add(dto);
        return dto;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    @ResponseBody
    public JwtResponse login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generateToken(principal.getId());
        return new JwtResponse(principal.getId(), principal.getUsername(), jwt);
    }

    //@PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/getById")
    @ResponseBody
    public ViewUserDto getById(@RequestParam long id) throws InternalServerException {
        return userService.getById(id);
    }

    @PostMapping("/searchWithPaging")
    @ResponseBody
    public List<ViewUserDto> searchWithPaging(@RequestBody GridParam param) throws InternalServerException {
        return userService.searchWithPaging(param);
    }
}
