package com.yxc.seabirdmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku销售属性值
 * 
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
@Data
@TableName("tb_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * attr_id
	 */
	private Long attrId;
	/**
	 * 属性名称
	 */
	private String attrName;
	/**
	 * 属性值
	 */
	private String attrValue;
	/**
	 * 属性排序
	 */
	private Integer attrSort;

}
