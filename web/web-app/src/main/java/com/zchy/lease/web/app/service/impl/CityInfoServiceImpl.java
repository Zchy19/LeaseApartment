package com.zchy.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.CityInfo;
import com.zchy.lease.web.app.service.CityInfoService;
import com.zchy.lease.web.app.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2024-11-03 11:12:39
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}




