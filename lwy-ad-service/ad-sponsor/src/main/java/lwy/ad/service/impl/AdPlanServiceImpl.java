package lwy.ad.service.impl;

import lwy.ad.constant.CommonStatus;
import lwy.ad.constant.Constants;
import lwy.ad.dao.AdPlanRepository;
import lwy.ad.dao.AdUserRepository;
import lwy.ad.entity.AdPlan;
import lwy.ad.entity.AdUser;
import lwy.ad.exception.AdException;
import lwy.ad.service.IAdPlanService;
import lwy.ad.utils.CommonUtils;
import lwy.ad.vo.AdPlanRequest;
import lwy.ad.vo.AdPlanResponse;
import lwy.ad.vo.AdplanGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;
    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //确保关联的user是存在的
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if(!adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_ERROR);
        }
        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(
                request.getUserId(),request.getPlanName()
        );
        if(oldPlan!=null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newAdPlan = planRepository.save(
                new AdPlan(request.getUserId(),request.getPlanName(),
                        CommonUtils.pareStringDate(request.getStartDate()),
                        CommonUtils.pareStringDate(request.getEndDate()))
        );

        return new AdPlanResponse(newAdPlan.getId(),newAdPlan.getPlanName());
    }

    @Override
    @Transactional
    public List<AdPlan> getAdPlanByIds(AdplanGetRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(
                request.getIds(),request.getUserId()
        );
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if(!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(),request.getUserId()
        );
        if(plan==null){
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_ERROR);
        }
        if(request.getPlanName()!=null){
            plan.setPlanName(request.getPlanName());
        }
        if(request.getStartDate()!=null){
            plan.setStartDate(
                    CommonUtils.pareStringDate(request.getStartDate())
            );
        }
        if(request.getEndDate()!=null){
            plan.setEndDate(
                    CommonUtils.pareStringDate(request.getEndDate())
            );
        }
        plan.setUpdateTime(new Date());
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(),plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {

        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CANNOT_FIND_ERROR);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }

}


