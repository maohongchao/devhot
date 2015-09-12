package com.bss.proj.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="t_order")
public class Order
{
	@Id
	private String orderId;
	
	private BigDecimal userId;
	
	private String address;
	
	private String phone;
	
	private Integer payWay;
	
	private String status;
	
	private Date createTime;
	
	private String delivery;
	
	private String comment;
	
	private Integer active;
	
}
