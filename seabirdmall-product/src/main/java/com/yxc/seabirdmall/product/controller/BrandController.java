package com.yxc.seabirdmall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.R;
import com.yxc.seabirdmall.common.valid.AddGroup;
import com.yxc.seabirdmall.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxc.seabirdmall.product.entity.BrandEntity;
import com.yxc.seabirdmall.product.service.BrandService;

import javax.validation.Valid;


/**
 * 品牌表
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("id") Integer id){
		BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand){
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Integer[] ids){
		brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
