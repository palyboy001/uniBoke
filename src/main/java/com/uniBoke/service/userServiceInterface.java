package com.uniBoke.service;

import com.uniBoke.entity.Register;
import com.uniBoke.entity.RequestMessage;
import com.uniBoke.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface userServiceInterface {
    // User login service:
    RequestMessage loginService(Register user);
    // User register service:
    RequestMessage registerService(Register register);
    // The phone verification code to log in:
    RequestMessage getCode(String phone, String index);
    // Check whether the authentication code is correct:
    RequestMessage checkCode(String phone, String index, String code);
    // Check whether the phone number is correct:
    RequestMessage checkPhone(Register register);
    // update information:
    RequestMessage upDateInformation(User user);
    // update user image:
    RequestMessage upLoadImageServer(MultipartFile file, String userRole);
    // get information to user
    RequestMessage getInformationServer(User user);
    // The token auto login
    RequestMessage tokenAutoLoginService(Register user);
}
