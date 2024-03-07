package com.husu.home.service.Impl;

import com.husu.home.mapper.UserMapper;
import com.husu.home.model.po.User;
import com.husu.home.model.request.UserAddRequest;
import com.husu.home.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author huyong(husu)
 * @date 2024/3/7 15:47
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public Boolean addUser(UserAddRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        long currentTime = System.currentTimeMillis();
        user.setCreateTime(currentTime);
        user.setCreateBy(request.getUserName());
        user.setUpdateTime(currentTime);
        user.setLastUpdateTime(currentTime);
        user.setLastUpdateBy(request.getUserName());
        user.setDeleted(false);

        userMapper.insert(user);

        return true;
    }
}
