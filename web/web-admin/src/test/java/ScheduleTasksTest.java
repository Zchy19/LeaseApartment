import com.zchy.AdminWebApplication;
import com.zchy.lease.web.admin.schedule.ScheduleTasks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: lease
 * @package: PACKAGE_NAME
 * @className: ScheduleTasks
 * @author: ZCH
 * @description:
 * @date: 8/6/2024 4:04 PM
 * @version: 1.0
 */
@SpringBootTest(classes = AdminWebApplication.class)
public class ScheduleTasksTest {

    @Autowired
    private ScheduleTasks scheduleTasks;

    @Test
    public void Test() {
        scheduleTasks.checkLeaseStatus();
    }
}
