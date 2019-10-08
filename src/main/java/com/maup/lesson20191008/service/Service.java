//package com.maup.lesson20191008.service;
//
//import dao.Dao;
//
//import java.util.List;
//
//public class Service<T> {
//    private Dao<T> dao;
//
//    public Service(){
//
//    }
//    public Service(Class<T> typeParameterClass) {
//        this.dao=new Dao<>(typeParameterClass);
//    }
//
//    public T findById(Long id) {
//        return dao.findById(id);
//    }
//
//    public void save(T user) {
//        dao.save(user);
//    }
//
//    public void update(T user) {
//        dao.update(user);
//    }
//
//    public void delete(T user) {
//        dao.delete(user);
//    }
//
//    public List<T> findAll() {
//        return dao.findAll();
//    }
//}