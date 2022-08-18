package com.yxc.seabirdmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.product.entity.AttrEntity;
import com.yxc.seabirdmall.product.vo.AttrRespVo;
import com.yxc.seabirdmall.product.vo.AttrVo;

import java.util.Map;

/**
 * 属性表
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:37
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attrVo);

    PageUtils queryBaseAttrpage(Map<String, Object> params, Long categoryId);

    AttrRespVo getAttrInfo(Long id);

    void updateAttr(AttrVo attrVo);
}

