package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.ApartmentInfo;
import com.zchy.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zchy.lease.web.admin.vo.apartment.ApartmentQueryVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.ApartmentInfo
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    List<ApartmentItemVo> getListByPage(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}




