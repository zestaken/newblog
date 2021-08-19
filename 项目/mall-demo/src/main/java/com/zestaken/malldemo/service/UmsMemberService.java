package com.zestaken.malldemo.service;

import com.zestaken.malldemo.api.CommonResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *会员管理Service接口
 * 使用Repository注解将service添加到springboot的对象容器（beans）中去，使其可以被自动注入（Autowired）
 * @author zestaken
 */
//todo 自动注入的要求条件
@Repository
@Service
public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
