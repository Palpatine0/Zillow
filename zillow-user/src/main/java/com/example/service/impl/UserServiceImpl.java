package com.example.service.impl;

import com.example.dao.LoginLogDao;
import com.example.dao.UserDao;
import com.example.dao.VerificationDao;
import com.example.entity.User;
import com.example.entity.VerificationCode;
import com.example.service.UserService;
import com.example.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private VerificationDao verificationDao;
    @Autowired
    private LoginLogDao loginLogDao;
    @Autowired
    private UserDao userDao;


    // aboard after spring security configured, this method wont execute anymore
    @Override
    public BaseResult login(String username, String password, String phone, String verificationCode) {
        /*LoginLog loginLog = new LoginLog();
        loginLog.setUsername(phone);
        loginLog.setLoginTime(new Date());
        loginLog.setType("1");*/

        User user = userDao.findUserByUsername(username);
        String name = user.getUsername();
        String pwd = user.getPassword();
        VerificationCode vCode4Check = verificationDao.getVerificationCode(phone);

        if (name == null) {
            System.out.println("username failed");
            return BaseResult.error("Enter your username");
        }
        if (password == null) {
            System.out.println("pwd failed");
            return BaseResult.error("Enter your password");
        }
        if (vCode4Check == null) {
            /*loginLog.setSuccess(false);
            loginLog.setMessage("Verification code outdated");
            loginLogDao.insertLoginLog(loginLog);*/
            System.out.println("v code failed");
            return BaseResult.error("Enter your verification code");
        }

        if (!password.equals(pwd)) {
            System.out.println("username unmatched");
            return BaseResult.error("Username or password code does not match");
        }
        if (!verificationCode.equals(vCode4Check.getVerificationCode())) {
            /*loginLog.setSuccess(false);
            loginLog.setMessage("In correct verification code");*/
            System.out.println("v code unmatched");
            return BaseResult.error("Verification code does not match");
        }

        /*loginLog.setSuccess(true);
        loginLog.setMessage("Successfully sign in");*/

        /*loginLogDao.insertLoginLog(loginLog);*/
        verificationDao.deleteVerificationCode(phone);
        return BaseResult.ok("Successfully sign in");
    }

    @Override
    public BaseResult register(String username, String password, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        if (username == "") {
            return BaseResult.error("Username cannot be empty");
        } else if (password == "") {
            return BaseResult.error("Password cannot be empty");
        }

        User user4Check = userDao.findUserByUsername(username);
        if (user4Check != null) {
            return BaseResult.error("This username had been used");
        } else {
            userDao.saveUser(user);
        }
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Successfully registered");
        return ok;
    }

    @Override
    public BaseResult deleteUserById(String id) {
        userDao.removeUserById(id);
        if (id == null) {
            BaseResult error = BaseResult.error();
            error.setMsg("Failed deleted, id cannot be empty");
            return error;
        }
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Successfully deleted");
        return ok;
    }

    @Override
    public BaseResult sendVerificationCode(String phone) {

        // S1: see if has verification code already there
        VerificationCode vCode4Check = verificationDao.getVerificationCode(phone);
        if (vCode4Check != null) {
            BaseResult error = BaseResult.error();
            error.setMsg("Verification code still valid, please do not repeatedly send verification code");
            return error;
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

        BaseResult ok = BaseResult.ok();
        ok.setMsg("Verification code sent");
        return ok;
    }

    @Override
    public BaseResult getUsers() {
        Query query = new Query();
        List<User> users = userDao.findUsers(query);
        return BaseResult.ok(users);
    }

    @Override
    public BaseResult getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return BaseResult.ok(user);
    }

    @Override
    public BaseResult getUserById(String id) {
        User user = userDao.findUserById(id);
        return BaseResult.ok(user);
    }

}
