package com.example.company.controller;

import com.example.company.model.Company;
import com.example.company.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private IService<Company> iService;

    @GetMapping
    private ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(iService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Company> companyOptional = iService.findById(id);
        if (companyOptional.isPresent()){
            return ResponseEntity.ok().body(companyOptional.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping
    private ResponseEntity<?> createCompany(@RequestBody Company company) {
        return new ResponseEntity<>(iService.save(company), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCompany(@RequestBody Company company) {
        Optional<Company> companyOptional = iService.findById(company.getId());
        if (companyOptional.isPresent()){
            iService.save(company);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        iService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getCompanyByName/{name}")
    private ResponseEntity<List<Company>> findAllByNameContaining(@PathVariable("name") String name) {
        return new ResponseEntity<>(iService.findCompaniesByNameContaining(name), HttpStatus.OK);
    }
}
