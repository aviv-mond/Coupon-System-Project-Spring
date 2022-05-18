package com.jb.CouponSystemProjectP2.Controllers;

import com.jb.CouponSystemProjectP2.Beans.Company;
import com.jb.CouponSystemProjectP2.Beans.Customer;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyException;
import com.jb.CouponSystemProjectP2.Exceptions.CompanyNotFoundException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerException;
import com.jb.CouponSystemProjectP2.Exceptions.CustomerNotFoundException;
import com.jb.CouponSystemProjectP2.Security.JWTutil;
import com.jb.CouponSystemProjectP2.Services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin") // http://localhost:8080/admin
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;
    private final JWTutil jwtUtil;

    //           --------------------GET------------------
    @GetMapping("/all/companies") // http://localhost:8080/admin/all/companies
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws CompanyNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readAllCompanies());
    }

    @GetMapping("/all/customers") // http://localhost:8080/admin/all/customers
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws CustomerNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readAllCustomers());
    }

    @GetMapping("/company/{id}") // http://localhost:8080/admin/company/{id}
    public ResponseEntity<?> getCompanyById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CompanyNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readCompanyById(id));
    }

    @GetMapping("/customer/{id}") // http://localhost:8080/admin/customer/{id}
    public ResponseEntity<?> getCustomerById(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CustomerNotFoundException {
        return ResponseEntity.ok()
                .header("Authorization", jwtUtil.generateToken(token))
                .body(administratorService.readCustomerById(id));
    }

    //          -------------------CREATE-------------------
    @PostMapping("/add/company") // http://localhost:8080/admin/add/company
    public ResponseEntity<?> createNewCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyException {
        administratorService.createCompany(company);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    @PostMapping("/add/customer") // http://localhost:8080/admin/add/customer
    public ResponseEntity<?> createNewCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerException {
        administratorService.createCustomer(customer);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    //          -------------------UPDATE----------------------
    @PutMapping("/update/company") // http://localhost:8080/admin/update/company
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyNotFoundException {
        administratorService.updateCompany(company);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    @PutMapping("/update/customer") // http://localhost:8080/admin/update/customer
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerNotFoundException {
        administratorService.updateCustomer(customer);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    //              -----------------DELETE------------------
    @DeleteMapping("/delete/company/{id}") // http://localhost:8080/admin/delete/company/{id}
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CompanyNotFoundException {
        administratorService.deleteCompanyById(id);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }

    @DeleteMapping("/delete/customer/{id}") // http://localhost:8080/admin/delete/customer/{id}
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws CustomerNotFoundException {
        administratorService.deleteCustomerById(id);
        return ResponseEntity.accepted()
                .header("Authorization", jwtUtil.generateToken(token))
                .build();
    }
}
