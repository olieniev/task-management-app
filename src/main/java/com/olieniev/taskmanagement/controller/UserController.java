package com.olieniev.taskmanagement.controller;

import com.olieniev.taskmanagement.dto.user.UpdateRoleRequestDto;
import com.olieniev.taskmanagement.dto.user.UpdateUserRequestDto;
import com.olieniev.taskmanagement.dto.user.UserResponseDto;
import com.olieniev.taskmanagement.model.User;
import com.olieniev.taskmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    @Operation(summary = "Update role method",
            description = "Updates user's role by user id if allowed")
    public UserResponseDto updateRole(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateRoleRequestDto requestDto,
            @PathVariable Long id) {
        return userService.updateRole(user, requestDto, id);
    }

    @GetMapping("/me")
    @Operation(summary = "Get info method",
            description = "Returns logged in users's info")
    public UserResponseDto getInfo(@AuthenticationPrincipal User user) {
        return userService.getInfo(user);
    }

    @PatchMapping("/me")
    @Operation(summary = "Update profile info method",
            description = "Updates profile method from provided dto")
    public UserResponseDto updateInfo(@AuthenticationPrincipal User user,
                                      @RequestBody @Valid UpdateUserRequestDto requestDto) {
        return userService.updateUser(user, requestDto);
    }
}
