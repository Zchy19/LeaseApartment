package com.zchy.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.BrowsingHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zchy.lease.web.app.vo.history.HistoryItemVo;

/**
* @author liubo
* @description 针对表【browsing_history(浏览历史)】的数据库操作Service
* @createDate 2024-11-03 11:12:39
*/
public interface BrowsingHistoryService extends IService<BrowsingHistory> {


    IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId);

    void saveHistory(Long userId, Long id);
}
