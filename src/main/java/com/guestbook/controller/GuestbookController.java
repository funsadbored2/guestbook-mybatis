package com.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guestbook.repository.GuestbookDao;
import com.guestbook.vo.GuestbookVo;




@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao dao;
	
	@RequestMapping("add")
	public String add(GuestbookVo vo) {

		 dao.add(vo);

		 return "redirect:/list";
	}
	
	@RequestMapping("deleteform/{no}")
	public String deleteform(@PathVariable("no") int no, Model model) {

		model.addAttribute("no", no);
		System.out.println(no);
		// delete전 비밀번호 확인하는 부분으로 사용자 입력 값 가져오기
		
		return "deleteform";

	}  
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		
		dao.delete(vo.getPassword(), vo.getNo());

		 return "redirect:/list";

	} 
	
	@RequestMapping("updateform")
	public String updateform() {

		System.out.println("updateform");

		return "updateform";

	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(GuestbookVo vo) {

		System.out.println("update");

		dao.update(vo);
		
		return "redirect:/list";

	} 
	
	@RequestMapping("list")
	public String list(Model model) {

		System.out.println("리스트로");

		List<GuestbookVo> list = dao.getList();

		model.addAttribute("list", list);

		return "list";

	}
	
	
}
