package lwy.ad.service;

import lwy.ad.entity.AdPlan;
import lwy.ad.exception.AdException;
import lwy.ad.vo.AdPlanRequest;
import lwy.ad.vo.AdPlanResponse;
import lwy.ad.vo.AdplanGetRequest;

import java.util.List;

public interface IAdPlanService {

    //创建推广计划
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;
    //获取推广计划
    List<AdPlan> getAdPlanByIds(AdplanGetRequest request) throws AdException;
    //AdPlan更新
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;
    //删除AdPlan
    void deleteAdPlan(AdPlanRequest request) throws AdException;

}
