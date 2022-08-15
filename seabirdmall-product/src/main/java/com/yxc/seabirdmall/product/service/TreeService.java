package com.yxc.seabirdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.product.entity.TreeEntity;

import java.util.Map;

/**
 * 
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:35
 */
public interface TreeService extends IService<TreeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

