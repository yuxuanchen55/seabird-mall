package com.yxc.seabirdmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.yxc.seabirdmall.common.utils.PageUtils;
import com.yxc.seabirdmall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxc.seabirdmall.product.entity.TreeEntity;
import com.yxc.seabirdmall.product.service.TreeService;



/**
 * 
 *
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:35
 */
@RestController
@RequestMapping("product/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:tree:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = treeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{nodeId}")
    //@RequiresPermissions("product:tree:info")
    public R info(@PathVariable("nodeId") Integer nodeId){
		TreeEntity tree = treeService.getById(nodeId);

        return R.ok().put("tree", tree);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:tree:save")
    public R save(@RequestBody TreeEntity tree){
		treeService.save(tree);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:tree:update")
    public R update(@RequestBody TreeEntity tree){
		treeService.updateById(tree);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:tree:delete")
    public R delete(@RequestBody Integer[] nodeIds){
		treeService.removeByIds(Arrays.asList(nodeIds));

        return R.ok();
    }

}
