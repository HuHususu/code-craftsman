package com.husu.home.controller;

import com.husu.common.model.GenericResponseEntity;
import com.husu.home.model.request.UserAddRequest;
import com.husu.home.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author huyong(husu)
 * @date 2024/3/7 15:45
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("husu-user")
@Tag(name = "用户服务", description = "用户服务")
public class UserController {
    private final UserService userService;

    @PostMapping("user")
    @Operation(summary = "新增用户", description = "该接口用于新增用户")
    public GenericResponseEntity<Boolean> addUser(@Valid @RequestBody UserAddRequest request) {
        return GenericResponseEntity.success(() -> userService.addUser(request));
    }
}
