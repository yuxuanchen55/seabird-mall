package com.yxc.seabirdmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.Query;
import com.yxc.seabirdmall.product.dao.AttrAttrgroupRelationDao;
import com.yxc.seabirdmall.product.dao.AttrGroupDao;
import com.yxc.seabirdmall.product.dao.CategoryDao;
import com.yxc.seabirdmall.product.entity.AttrAttrgroupRelationEntity;
import com.yxc.seabirdmall.product.entity.AttrGroupEntity;
import com.yxc.seabirdmall.product.entity.CategoryEntity;
import com.yxc.seabirdmall.product.service.AttrAttrgroupRelationService;
import com.yxc.seabirdmall.product.service.CategoryService;
import com.yxc.seabirdmall.product.vo.AttrRespVo;
import com.yxc.seabirdmall.product.vo.AttrVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryService categoryService;

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
     *
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

    /**
     * 获取指定分类的所有基本属性列表
     *
     * @param params
     * @param categoryId
     * @return
     */
    @Override
    public PageUtils queryBaseAttrpage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        if (categoryId != 0) {
            wrapper.eq("category_id", categoryId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(t -> t.eq("id", key).or().like("name", key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper
        );

        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map(attrEntity -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            // 1. 封装基础属性内容
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            // 2, 封装属性分组名称
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                    new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getId())
            );
            if (attrAttrgroupRelationEntity != null) {
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if (attrGroupEntity != null) {
                    attrRespVo.setGroupName(attrGroupEntity.getName());
                }
            }
            // 3. 封装分组名称
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCategoryId());
            if (categoryEntity != null) {
                attrRespVo.setCategoryName(categoryEntity.getName());
            }

            return attrRespVo;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(respVos);

        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long id) {
        AttrEntity attrEntity = this.getById(id);
        AttrRespVo attrRespVo = new AttrRespVo();
        BeanUtils.copyProperties(attrEntity, attrRespVo);
        // 1. 设置分组信息
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", id)
        );
        if (attrAttrgroupRelationEntity != null) {
            attrRespVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
            if (attrGroupEntity != null) {
                attrRespVo.setGroupName(attrGroupEntity.getName());
            }
        }
        // 2. 设置分类路径
        Long[] categoryPath = categoryService.findCategoryPath(attrRespVo.getCategoryId());
        attrRespVo.setCategoryPath(categoryPath);

        // 3. 设置分类名称
        CategoryEntity categoryEntity = categoryDao.selectById(attrRespVo.getCategoryId());
        if (categoryEntity != null) {
            attrRespVo.setCategoryName(categoryEntity.getName());
        }

        return attrRespVo;
    }

    /**
     * 更新基本属性信息
     *
     * @param attrVo
     */
    @Override
    public void updateAttr(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        this.updateById(attrEntity);

        // 修改属性分组关联表
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getId());

        Integer count = attrAttrgroupRelationDao.selectCount(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getId())
        );
        if (count > 0) {
            attrAttrgroupRelationDao.update(relationEntity,
                    new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getId())
            );
        } else {
            attrAttrgroupRelationDao.insert(relationEntity);
        }
    }

}