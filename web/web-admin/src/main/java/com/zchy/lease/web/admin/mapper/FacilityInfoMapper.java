package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.FacilityInfo;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.FacilityInfo
*/
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    List<FacilityInfo> getByApartmentId(Long id);

    List<FacilityInfo> getByRoomId(Long id);
}




