package com.husu.home.controller;

import com.husu.common.model.GenericResponseEntity;
import com.husu.home.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/5/30 23:35
 */
@RestController
@RequestMapping("home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("hello")
    @Operation(summary = "home", description = "测试")
    public GenericResponseEntity<String> hello() {
        return GenericResponseEntity.success(() -> homeService.hello());
    }
}
