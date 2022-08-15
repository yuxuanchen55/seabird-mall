package com.yxc.seabirdmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌表
 * 
 * @author yuxuan chen
 * @email chenyuxuang@gmail.com
 * @date 2022-08-09 00:02:36
 */
@Data
@TableName("tb_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@NotNull(message = "修改必须制定品牌id")
	@Null(message = "新增不能制定品牌id")
	private Integer id;
	/**
	 * 品牌名称
	 */
	@NotBlank(message = "品牌名必须提交")
	private String name;
	/**
	 * 品牌图片地址
	 */
	@URL(message = "品牌图片地址必须是一个有效的url")
	private String image;
	/**
	 * 品牌的首字母
	 */
	@NotEmpty()
	@Pattern(regexp = "^[a-zA-Z]$", message = "品牌的首字母必须是一个字母")
	private String letter;
	/**
	 * 排序
	 */
	@NotNull
	@Min(value = 0, message = "排序必须是一个正整数")
	private Integer seq;

}
