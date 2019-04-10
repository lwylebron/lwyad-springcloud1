package lwy.ad.service.impl;

import lombok.extern.slf4j.Slf4j;
import lwy.ad.constant.Constants;
import lwy.ad.dao.AdUserRepository;
import lwy.ad.entity.AdUser;
import lwy.ad.exception.AdException;
import lwy.ad.service.IUserService;
import lwy.ad.utils.CommonUtils;
import lwy.ad.vo.CreateUserRequest;
import lwy.ad.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request)
            throws AdException {

        //请求参数异常，用户名不为空
        if(!request.validate()){
            throw  new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        //用户名不能同名
        AdUser oldUser  =userRepository.findByUsername(request.getUsername());
        if(oldUser!=null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
                ));
        return new CreateUserResponse(
                newUser.getId(),newUser.getUsername(),newUser.getToken(),
                newUser.getCreateTime(),newUser.getUpdateTime()
        );


    }
}
