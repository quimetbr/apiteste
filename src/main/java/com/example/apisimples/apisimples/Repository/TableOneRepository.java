package com.example.apisimples.apisimples.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apisimples.apisimples.Entity.TableOne;

@Repository
public interface TableOneRepository extends JpaRepository<TableOne, Integer>  {

}

