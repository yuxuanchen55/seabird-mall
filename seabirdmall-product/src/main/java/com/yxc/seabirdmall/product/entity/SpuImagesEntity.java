package com.yxc.seabirdmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * spu图片
 * 
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:35
 */
@Data
@TableName("tb_spu_images")
public class SpuImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * spu_id
	 */
	private Long spuId;
	/**
	 * 图片名称
	 */
	private String imgName;
	/**
	 * 图片url
	 */
	private String imgUrl;
	/**
	 * 图片顺序
	 */
	private Integer imgSort;
	/**
	 * 默认图片
	 */
	private Integer defaultImg;

}
