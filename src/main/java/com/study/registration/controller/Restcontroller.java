package com.study.registration.controller;

import com.study.registration.entity.registration;
import com.study.registration.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class Restcontroller {
    private Service theservice;

    public Restcontroller(Service myservice){
        theservice=myservice;
    }

    @GetMapping("/list")
    public List<registration> displayall(){

       return theservice.findall();
    }

    @GetMapping("/list/{myid}")
    public registration searchbyid(@PathVariable int myid){
      registration thereg=theservice.findbyid(myid);

      if(thereg==null){
          throw new RuntimeException("there is no such id:"+myid+ " in our system");
      }
      return thereg;
    }
    @PostMapping("/add")
    public registration addnewstudent(@RequestBody registration thereg){
        thereg.setId(thereg.getId());
        theservice.save(thereg);
        return thereg;

    }

    @PutMapping("/update")
    public registration updatestudent(@RequestBody registration thereg){
        theservice.save(thereg);

        return thereg;
    }

    @DeleteMapping("/delete/{myid}")
    public String delete(@PathVariable int myid){

        registration thereg=theservice.findbyid(myid);
        if (thereg==null){
            throw new RuntimeException("there is no id with that id to delete");
        }
       theservice.delete(myid);
        return "student has deleted with id:"+myid;
    }


}
