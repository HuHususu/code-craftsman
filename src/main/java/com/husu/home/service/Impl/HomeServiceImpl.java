package com.husu.home.service.Impl;

import com.husu.common.exceptions.BizException;
import com.husu.home.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/5/30 23:37
 */
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public String hello() {
        log.info("我是日志*********************************啦啦啦啦啦.");
        throw new BizException("test");
    }
}
