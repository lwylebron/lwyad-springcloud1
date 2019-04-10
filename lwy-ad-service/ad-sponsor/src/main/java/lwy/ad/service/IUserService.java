package lwy.ad.service;

import lwy.ad.exception.AdException;
import lwy.ad.vo.CreateUserRequest;
import lwy.ad.vo.CreateUserResponse;

public interface IUserService {

    //创建用户
    CreateUserResponse createUser(CreateUserRequest request)
               throws AdException;
}
