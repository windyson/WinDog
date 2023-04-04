package com.yunbot.windog.shiro.service;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import static com.yunbot.windog.controller.security.SM3Utils.generateSM3HASH;

public class SM3CredentialsMatcher extends SimpleCredentialsMatcher {

    //by william 2021-4-26
    //采用国密sm3杂凑算法，对密码进行加密，本方法主要实现验证密码是否正确

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        return equals(generateSM3HASH(new String(token.getPassword())), info.getCredentials());
    }


}
