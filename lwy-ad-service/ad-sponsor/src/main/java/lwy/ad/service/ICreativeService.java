package lwy.ad.service;

import lwy.ad.vo.CreativeRequest;
import lwy.ad.vo.CreativeResponse;

public interface ICreativeService {
    CreativeResponse createCreative(CreativeRequest request);
}
