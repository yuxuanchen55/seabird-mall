package com.yxc.seabirdmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性表
 * 
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:37
 */
@Data
@TableName("tb_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 搜索类型
	 */
	private Integer searchType;
	/**
	 * 图表
	 */
	private String icon;
	/**
	 * 选择值
	 */
	private String valueSelect;
	/**
	 * 属性类型:0-销售属性,1-基本属性,2-既是基本属性又是销售属性
	 */
	private Integer attrType;
	/**
	 * 启用
	 */
	private Long enable;
	/**
	 * 分类ID
	 */
	private Integer categoryId;
	/**
	 * 描述
	 */
	private Integer showDesc;

}
