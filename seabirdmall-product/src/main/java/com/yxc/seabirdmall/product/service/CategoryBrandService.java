package com.yxc.seabirdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.product.entity.CategoryBrandEntity;

import java.util.Map;

/**
 * 分类品牌关系表
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
public interface CategoryBrandService extends IService<CategoryBrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

