package com.yxc.seabirdmall.product.service.impl;

import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yxc.seabirdmall.product.dao.CategoryDao;
import com.yxc.seabirdmall.product.entity.CategoryEntity;
import com.yxc.seabirdmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * Query All Categories and organize with tree style
     *
     * @return
     */
    @Override
    public List<CategoryEntity> listWithThree() {
        List<CategoryEntity> entities = baseMapper.selectList(null);
        List<CategoryEntity> levelOneMenus = entities.stream().filter(
                        category -> category.getParentId() == 0
                )
                .peek(menu -> menu.setChildren(getChildren(menu, entities))).collect(Collectors.toList());

        return levelOneMenus;
    }

    @Override
    public Long[] findCategoryPath(Integer categoryId) {
        List<Long> path = new ArrayList<>();
        findParentPath(categoryId, path);
        Collections.reverse(path);
        return path.toArray(new Long[0]);
    }

    private void findParentPath(Integer categoryId, List<Long> path) {
        path.add(categoryId.longValue());
        CategoryEntity category = this.getById(categoryId);

        if (category.getParentId() != 0) {
            findParentPath(category.getParentId(), path);
        }
    }


    private List<CategoryEntity> getChildren(CategoryEntity menu, List<CategoryEntity> entities) {
        return entities.stream().filter(
                category -> Objects.equals(category.getParentId(), menu.getId())
        ).peek(category -> category.setChildren(getChildren(category, entities))).collect(Collectors.toList());
    }


}