package com.yxc.seabirdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品类目
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithThree();

    Long[] findCategoryPath(Integer categoryId);
}

