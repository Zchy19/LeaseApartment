package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.ViewAppointment;
import com.zchy.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.zchy.lease.web.admin.vo.appointment.AppointmentVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.ViewAppointment
*/
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    List<AppointmentVo> getAllList(Page<AppointmentVo> page, AppointmentQueryVo queryVo);
}




