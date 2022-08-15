package com.study.registration.service;

import com.study.registration.Dao.Registrationrep;
import com.study.registration.entity.registration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Registrationservice implements com.study.registration.service.Service {

    private Registrationrep repository;

    public Registrationservice(Registrationrep therep){
        repository=therep;
    }


    @Override
    public List<registration> findall() {
        return repository.findAll();
    }

    @Override
    public registration findbyid(int myid) {
        Optional<registration> result=repository.findById(myid);
        registration thereg=null;
        if(result.isPresent()){
            thereg=result.get();

        }
        else {
            throw new RuntimeException("there is data for that id of:  "+myid);
        }
        return thereg;
    }



    @Override
    public void save(registration thereg) {
     repository.save(thereg);

    }

    @Override
    public void delete(int myid) {

        repository.deleteById(myid);

    }
}
