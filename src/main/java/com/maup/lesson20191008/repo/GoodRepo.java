package com.maup.lesson20191008.repo;

import com.maup.lesson20191008.model.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepo extends JpaRepository<Good,String> {
}
