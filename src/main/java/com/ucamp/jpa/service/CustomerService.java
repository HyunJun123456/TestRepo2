package com.ucamp.jpa.service;

import com.ucamp.jpa.dto.req.CustomerReqDto;
import com.ucamp.jpa.dto.req.CustomerReqForm;
import com.ucamp.jpa.dto.res.CustomerRespDto;
import com.ucamp.jpa.entity.Customer;
import com.ucamp.jpa.exception.BusinessException;
import com.ucamp.jpa.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;

    // 고객 생성 (Create)
    public CustomerRespDto makeCustomer(CustomerReqDto customerReqDto) {
        // reqDto -> Entity
        Customer customer = modelMapper.map(customerReqDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerRespDto.class);
    }

    // (Update)
    public CustomerRespDto changeCustomer(String email, CustomerReqDto customerReqDto) {
        Customer customerEntity = customerRepository.findByEmail(email).orElseThrow(() ->
                new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        customerEntity.setName(customerReqDto.getName());
        customerRepository.save(customerEntity);
        return modelMapper.map(customerEntity, CustomerRespDto.class);
    }

    // 아이디로 고객 찾기 (Read)
    public CustomerRespDto fetchCustomer(Long id) {
        Customer customerEntity = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(customerEntity, CustomerRespDto.class);
    }

    // 전체 고객 찾기(AllRead)
    public List<CustomerRespDto> fetchCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map(customer -> modelMapper.map(customer, CustomerRespDto.class))
                .collect(Collectors.toList());
    }

    // 고객 삭제(Delete)
    public void removeCustomer(Long id) {
        Customer customerEntity = customerRepository.findById(id).orElseThrow(() -> new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customerEntity);
    }

    public void customerUpdate(CustomerReqForm customerReqForm) {
        Customer existingCustomer = customerRepository.findById(customerReqForm.getId()).orElseThrow(()-> new BusinessException(customerReqForm.getId() +" Customer Not Found", HttpStatus.NOT_FOUND));
        existingCustomer.setName(customerReqForm.getName());
        customerRepository.save(existingCustomer);
    }
}
