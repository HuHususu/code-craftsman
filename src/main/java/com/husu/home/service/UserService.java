package com.husu.home.service;

import com.husu.home.model.request.UserAddRequest;

/**
 * @author huyong(husu)
 * @date 2024/3/7 15:47
 */
public interface UserService {
    Boolean addUser(UserAddRequest request);
}
