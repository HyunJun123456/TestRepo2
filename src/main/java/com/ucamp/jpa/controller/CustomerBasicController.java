package com.ucamp.jpa.controller;

import com.ucamp.jpa.dto.req.CustomerReqDto;
import com.ucamp.jpa.dto.req.CustomerReqForm;
import com.ucamp.jpa.dto.res.CustomerRespDto;
import com.ucamp.jpa.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerBasicController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public ModelAndView myIndex(Model model) {
        List<CustomerRespDto> customerList = customerService.fetchCustomers();
//        model.addAttribute("customers", customerList);
//        return "index";
        return new ModelAndView("index", "customers", customerList);
    }

    //BindingResult는 자동으로 view에 넘어가기때문에, Model에 안담아도 된다.
    @GetMapping("/signup")
    public String showSignUpForm(CustomerReqDto customerReqDto, Model model) {
        model.addAttribute("customer", customerReqDto);
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@Valid CustomerReqDto customerReqDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customerReqDto);
            return "add-customer";
        }
        customerService.makeCustomer(customerReqDto);
        model.addAttribute("customers", customerService.fetchCustomers());
        return "redirect:/customers/index";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateFrom(@PathVariable Long id, Model model) {
        CustomerRespDto customerRespDto = customerService.fetchCustomer(id);
        model.addAttribute("customers", customerRespDto);
        return "update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@Valid @ModelAttribute("customer") CustomerReqForm customerReqForm, BindingResult result, Model model, @PathVariable Long id) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerReqForm);
            model.addAttribute("errors", result.getAllErrors());
            // System.out.println(result.getAllErrors());
            return "update-customer";
        }
        customerService.customerUpdate(customerReqForm);
        return "redirect:/customers/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.removeCustomer(id);
        return "redirect:/customers/index";
    }

}