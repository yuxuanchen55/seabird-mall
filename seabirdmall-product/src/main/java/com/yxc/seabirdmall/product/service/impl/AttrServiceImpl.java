package com.yxc.seabirdmall.product.service.impl;

import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.Query;
import com.yxc.seabirdmall.product.dao.AttrAttrgroupRelationDao;
import com.yxc.seabirdmall.product.entity.AttrAttrgroupRelationEntity;
import com.yxc.seabirdmall.product.service.AttrAttrgroupRelationService;
import com.yxc.seabirdmall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yxc.seabirdmall.product.dao.AttrDao;
import com.yxc.seabirdmall.product.entity.AttrEntity;
import com.yxc.seabirdmall.product.service.AttrService;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 在attr标中保存基本属性信息，然后在关联表中保存关联信息
     * @param attrVo
     */
    @Override
    public void saveAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);

        this.save(attrEntity);
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getId());
        attrAttrgroupRelationDao.insert(relationEntity);

    }

}