package com.zchy.lease.web.admin.schedule;

import com.zchy.lease.web.admin.service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @projectName: lease
 * @package: com.zchy.lease.web.admin.schedule
 * @className: ScheduleTasks
 * @author: ZCH
 * @description:
 * @date: 8/6/2024 3:56 PM
 * @version: 1.0
 */
@Component
public class ScheduleTasks {
    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {
        leaseAgreementService.updateStatus();
    }
}
