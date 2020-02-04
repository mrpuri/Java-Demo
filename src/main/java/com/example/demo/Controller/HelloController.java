package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.City;
import com.example.demo.model.ICityService;
import com.example.demo.test.IUserService;
import com.example.demo.test.Users;




@Controller
public class HelloController {
    @GetMapping({"/"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
    
    @GetMapping({"/hello"})
    public String henllo(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "index";
    }
 
    
    @Autowired
    private ICityService cityService;

    @GetMapping("/showCities")
    public String findCities(Model model) {

        List<City> cities = (List<City>) cityService.findAll();

        model.addAttribute("cities", cities);

        return "showCities";
    }
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String findUsers(Model model, @RequestParam String username) {
        List<Users> User = (List<Users>) userService.findAll();  
               	
        	for(Users obj: User)
        	{
        		System.out.println(obj.getUsername());
        		if(obj.getUsername().equals(username) && obj.getUsertype().equalsIgnoreCase("Admin") )
           	 {
           		 System.out.println("2");
           		 return "admin";
           	 }
           	 else if(obj.getUsername() == username && obj.getUsertype() == "student" )
           	 {
           		 System.out.println("3");
           		 return "student";
           	 }
        	}
        	 
         
        return "hello";
        
        
     
    }
   
    
    @PostMapping("/create")
    public String create(@RequestBody Users user){
    // save a single Customer
    	userService.Create(user);
    return "hello";
    }
}







