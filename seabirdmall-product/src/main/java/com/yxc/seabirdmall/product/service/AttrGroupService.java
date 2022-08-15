package com.yxc.seabirdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组表
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:37
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long categoryId);
}

