package com.ucamp.jpa.controller;

import com.ucamp.jpa.dto.req.CustomerReqDto;
import com.ucamp.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 전체 고객 찾기
    @GetMapping()
    public ResponseEntity<?> fetchCustomers() {
        return ResponseEntity.ok(customerService.fetchCustomers());
    }

    // 고객 추가
    @PostMapping()
    public ResponseEntity<?> makeCustomer(@RequestBody CustomerReqDto customerReqDto) {
        return ResponseEntity.ok(customerService.makeCustomer(customerReqDto));
    }

    // 고객 삭제
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable Long id) {
        customerService.removeCustomer(id);
        return ResponseEntity.ok(id + " Customer가 삭제처리 되었습니다.");
    }

    // 고객 변경
    @PatchMapping("/email/{email}")
    public ResponseEntity<?> changeCustomer(@PathVariable String email, @RequestBody CustomerReqDto customerReqDto) {
        return ResponseEntity.ok(customerService.changeCustomer(email, customerReqDto));
    }


}
