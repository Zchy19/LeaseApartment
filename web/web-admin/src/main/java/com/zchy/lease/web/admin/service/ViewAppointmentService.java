package com.zchy.lease.web.admin.service;

import com.zchy.lease.model.entity.ViewAppointment;
import com.zchy.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.zchy.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
* @createDate 2024-11-03 15:48:00
*/
public interface ViewAppointmentService extends IService<ViewAppointment> {

    IPage<AppointmentVo> getAllList(long current, long size, AppointmentQueryVo queryVo);
}
