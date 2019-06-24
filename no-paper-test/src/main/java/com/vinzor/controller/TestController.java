package com.vinzor.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinzor.entity.Demo;

@Controller
public class TestController {

	@RequestMapping(value="/look")
	public String demo(Model model) {
		model.addAttribute("name","爆山大爷");
		List<Demo> list=new ArrayList<>();
		list.add(new Demo("a","b"));
		list.add(new Demo("c","d"));
		list.add(new Demo("e","f"));
		Demo demo=new Demo("a","b");
		model.addAttribute("demo",demo);
		model.addAttribute("list", list);
		return "index";
	}
	@RequestMapping(value="/look2",method=RequestMethod.POST)
	@ResponseBody
	public String demo(@RequestBody String json) {
		System.out.println(json);
		return "爆山大爷";
	}
}
