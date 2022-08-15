package com.study.registration.service;

import com.study.registration.entity.registration;

import java.util.List;

public interface Service {
    public List<registration> findall();
    public registration findbyid(int myid);
    public void save(registration thereg);
    public void delete(int myid);
}
