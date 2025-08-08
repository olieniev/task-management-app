package com.olieniev.taskmanagement.controller;

import com.olieniev.taskmanagement.dto.user.UpdateRoleRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User controller",
        description = "User controller methods")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    public UserResponseDto updateRole(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateRoleRequestDto requestDto,
            @PathVariable Long id) {
        return userService.updateRole(user, requestDto, id);
    }
}
