package org.wzp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * @author wzp
 * @date: 2018年4月2日 下午10:09:46 
 *
 */
@Controller
public class PageController {
	@RequestMapping("index")
	public String indexPage() {
		return "welcome.html";
	}
}
