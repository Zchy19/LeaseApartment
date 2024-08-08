package com.zchy.lease.web.admin.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.common.result.Result;
import com.zchy.lease.model.entity.UserInfo;
import com.zchy.lease.model.enums.BaseStatus;
import com.zchy.lease.web.admin.service.UserInfoService;
import com.zchy.lease.web.admin.vo.user.UserInfoQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "分页查询用户信息")
    @GetMapping("page")
    public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size, UserInfoQueryVo queryVo) {
        IPage<UserInfo> page = new Page<>(current, size);
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryVo.getPhone() != null, UserInfo::getPhone, queryVo.getPhone())
                .eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus());
        IPage<UserInfo> userInfoIPage = userInfoService.page(page, wrapper);
        return Result.ok(userInfoIPage);
    }

    @Operation(summary = "根据用户id更新账号状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<UserInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserInfo::getId, id)
                .set(UserInfo::getStatus, status);
        userInfoService.update(wrapper);
        return Result.ok();
    }
}