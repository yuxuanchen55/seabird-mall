package com.yxc.seabirdmall.product.service.impl;

import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yxc.seabirdmall.product.dao.TreeDao;
import com.yxc.seabirdmall.product.entity.TreeEntity;
import com.yxc.seabirdmall.product.service.TreeService;


@Service("treeService")
public class TreeServiceImpl extends ServiceImpl<TreeDao, TreeEntity> implements TreeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TreeEntity> page = this.page(
                new Query<TreeEntity>().getPage(params),
                new QueryWrapper<TreeEntity>()
        );

        return new PageUtils(page);
    }

}