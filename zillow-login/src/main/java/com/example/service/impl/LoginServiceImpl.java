package com.example.service.impl;

import com.example.dao.LoginLogDao;
import com.example.dao.VerificationDao;
import com.example.entity.LoginLog;
import com.example.entity.VerificationCode;
import com.example.service.LoginService;
import com.example.vo.ZillowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private VerificationDao verificationDao;
    @Autowired
    private LoginLogDao loginLogDao;


    @Override
    public ZillowResult sendVerificationCode(String phone) {

        // S1: see if has verification code already there
        VerificationCode vCode4Check = verificationDao.getVerificationCode(phone);
        if (vCode4Check != null) {
            return ZillowResult.error("Verification code still valid, please do not repeatedly send verification code");
        }

        // S2: build new verification code if does not exist
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        String rawCode = sb.toString();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setPhone(phone);
        verificationCode.setVerificationCode(rawCode);

        verificationDao.setVerificationCode(verificationCode.getPhone(), verificationCode);

        ZillowResult result = ZillowResult.ok();
        result.setMsg("Verification code sent");
        System.out.println("phone: " + phone + " code: " + rawCode);
        return result;
    }

    @Override
    public ZillowResult login(String phone, String verificationCode) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(phone);
        loginLog.setLoginTime(new Date());
        loginLog.setType("1");

        VerificationCode vCode4Check = verificationDao.getVerificationCode(phone);
        if (vCode4Check == null) {
            loginLog.setSuccess(false);
            loginLog.setMessage("Verification code outdated");
            loginLogDao.insertLoginLog(loginLog);
            return ZillowResult.error("Invalid phone number or verification code");
        }

        if (!verificationCode.equals(vCode4Check.getVerificationCode())) {
            loginLog.setSuccess(false);
            loginLog.setMessage("In correct verification code");
            return ZillowResult.error("Invalid phone number or verification code");
        }

        loginLog.setSuccess(true);
        loginLog.setMessage("Successfully sign in");

        loginLogDao.insertLoginLog(loginLog);
        verificationDao.deleteVerificationCode(phone);
        return ZillowResult.ok("Successfully sign in");
    }
}
