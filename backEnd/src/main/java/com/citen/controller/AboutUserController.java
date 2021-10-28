package com.citen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/* import org.springframework.stereotype.Controller; */
import org.springframework.web.bind.annotation.PostMapping;
/* import org.springframework.web.bind.annotation.PathVariable; */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citen.repository.AboutUserRepository;
import com.citen.model.AboutUser;

import org.springframework.beans.factory.annotation.Autowired;

/* @Controller */
@RestController
@RequestMapping(path = "/aboutuser")
public class AboutUserController {

    private Logger logger = LoggerFactory.getLogger(AboutUserController.class);

    @Autowired
    private AboutUserRepository aboutUserRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewAboutUser(@RequestParam Integer idUser, @RequestParam String city,
            @RequestParam String street, @RequestParam String zipcode, @RequestParam String numhome,
            @RequestParam String numapartment) {
        AboutUser au = new AboutUser();
        au.setIdUser(idUser);
        au.setCity(city);
        au.setStreet(street);
        au.setZipcode(zipcode);
        au.setNumhome(numhome);
        au.setNumapartment(numapartment);
        aboutUserRepository.save(au);

        return "add aboutUser for iduser=" + au.getIdUser();
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateAboutUser(@RequestParam Integer id, @RequestParam Integer idUser,
            @RequestParam String city, @RequestParam String street, @RequestParam String zipcode,
            @RequestParam String numhome, @RequestParam String numapartment) {
        /*
         * customer.setId(id); Customer savedCustomer =
         * customerRepository.save(customer); return new
         * ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
         */
        try {
            if (aboutUserRepository.findById(id).isPresent()) {
                AboutUser au = aboutUserRepository.findById(id).get();

                au.setId(id);
                au.setIdUser(idUser);
                au.setCity(city);
                au.setStreet(street);
                au.setZipcode(zipcode);
                au.setNumhome(numhome);
                au.setNumapartment(numapartment);

                AboutUser savedAu = aboutUserRepository.save(au);

                return new ResponseEntity<Object>(savedAu, HttpStatus.OK);
            }else{
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteAbouUser(@RequestParam Integer id) {
        aboutUserRepository.deleteById(id);

        return "delete id=" + id;
    }

    @PostMapping(path = "/all")
    private @ResponseBody Iterable<AboutUser> selectAll() {
        return aboutUserRepository.findAll();
    }

}