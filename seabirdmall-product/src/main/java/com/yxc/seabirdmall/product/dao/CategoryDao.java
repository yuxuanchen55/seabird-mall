package com.yxc.seabirdmall.product.dao;

import com.yxc.seabirdmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类目
 * 
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
