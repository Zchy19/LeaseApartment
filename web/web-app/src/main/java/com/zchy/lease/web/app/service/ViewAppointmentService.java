package com.zchy.lease.web.app.service;

import com.zchy.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zchy.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.zchy.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
* @createDate 2024-11-03 11:12:39
*/
public interface ViewAppointmentService extends IService<ViewAppointment> {
    List<AppointmentItemVo> listItemByUserId(Long id);

    AppointmentDetailVo getDetailById(Long id);
}
