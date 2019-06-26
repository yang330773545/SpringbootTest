package com.yang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.entity.User;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
/**
 * 
 *  作用范围	API	使用位置
 *	对象属性	@ApiModelProperty	用在出入参数对象的字段上
 *	协议集描述	@Api	用于controller类上
 *	协议描述	@ApiOperation	用在controller的方法上
 *	Response集	@ApiResponses	用在controller的方法上
 *	Response	@ApiResponse	用在 @ApiResponses里边
 *	非对象参数集	@ApiImplicitParams	用在controller的方法上
 *	非对象参数描述	@ApiImplicitParam	用在@ApiImplicitParams的方法里边
 *	描述返回对象的意义	@ApiModel	用在返回对象类上
 *
 */
@RestController
public class TestController {

	@ApiOperation(value = "查询测试", notes = "查询测试1")
	@GetMapping("/getuser")
	public String getUser() {
		return "user";
	}
	@ApiOperation(value = "添加测试", notes = "添加测试1")
	@PostMapping("/adduser")
	public boolean addUser(User user) {
		return true;
	}
	
}
