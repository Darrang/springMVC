package com.javax.cseeapp;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javax.cseeapp.bean.Emp;
import com.javax.cseeapp.dao.EmpDao;


@Controller
public class EmpController {

	@Autowired
	private EmpDao empDao;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String empList(Locale locale, Model model) {
		model.addAttribute("list",empDao.getList());
		return "redirect:/viewemp";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") Emp emp) {
		empDao.save(emp);
		return "redirect:/viewemp";
	}
	
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("command",new Emp());
		return "empform";
	}
	
    @RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Emp> list=empDao.getList();
		m.addAttribute("list", list);
		return "viewemp";
	}
	
    @RequestMapping(value="/editsave",method = RequestMethod.POST)    
    public String editsave(@ModelAttribute("emp") Emp emp){    
        empDao.update(emp);    
        return "redirect:/viewemp";    
    }   
    
	@RequestMapping(value="/editemp/{id}")
	public String edit(@PathVariable int id, Model m) {
		Emp emp=empDao.getEmpById(id);
		m.addAttribute("command", emp);
		return "empeditform";
	} 
	
	@RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		empDao.delete(id);
		return "redirect:/viewemp";
	}
		
}
