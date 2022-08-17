package com.yxc.seabirdmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.R;
import com.yxc.seabirdmall.product.service.BrandService;
import com.yxc.seabirdmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxc.seabirdmall.product.entity.CategoryBrandEntity;
import com.yxc.seabirdmall.product.service.CategoryBrandService;


/**
 * 分类品牌关系表
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
@RestController
@RequestMapping("product/categorybrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/category/list")
    //@RequiresPermissions("product:categorybrand:list")
    public R list(@RequestParam("brandId") Long brandId) {
        List<CategoryBrandEntity> data = categoryBrandService.list(
                new QueryWrapper<CategoryBrandEntity>().eq("brand_id", brandId)
        );
        for (CategoryBrandEntity categoryBrandEntity : data) {
            categoryBrandEntity.setCategoryName(categoryService.getById(categoryBrandEntity.getCategoryId()).getName());
            categoryBrandEntity.setBrandName(brandService.getById(categoryBrandEntity.getBrandId()).getName());
        }
        return R.ok().put("data", data);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:categorybrand:info")
    public R info(@PathVariable("id") Integer id) {
        CategoryBrandEntity categoryBrand = categoryBrandService.getById(id);

        return R.ok().put("categoryBrand", categoryBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:categorybrand:save")
    public R save(@RequestBody CategoryBrandEntity categoryBrand) {
        categoryBrandService.save(categoryBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:categorybrand:update")
    public R update(@RequestBody CategoryBrandEntity categoryBrand) {
        categoryBrandService.updateById(categoryBrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:categorybrand:delete")
    public R delete(@RequestBody CategoryBrandEntity categoryBrandEntity) {
//        categoryBrandService.removeByIds(Arrays.asList(ids));
        categoryBrandService.remove(
                new QueryWrapper<CategoryBrandEntity>().eq("category_id", categoryBrandEntity.getCategoryId())
                        .eq("brand_id", categoryBrandEntity.getBrandId())
        );
        return R.ok();
    }

}
