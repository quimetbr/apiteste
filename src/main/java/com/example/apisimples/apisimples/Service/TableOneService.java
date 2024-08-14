package com.example.apisimples.apisimples.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apisimples.apisimples.Entity.TableOne;
import com.example.apisimples.apisimples.Repository.TableOneRepository;

@Service
public class TableOneService {

    @Autowired
    TableOne tableOne;

    @Autowired
    TableOneRepository tableOneRepository;

    
    
    public TableOne postTableOne(TableOne tableOne) {
        return tableOneRepository.save(tableOne);
    }

    public void deleteTableOne(Integer id) {
		tableOneRepository.deleteById(id);
	}

    //public Optional<TableOne> getRegister(Integer id) {
    public Optional<TableOne> getRegister(Integer id) {
		return tableOneRepository.findById(id);
	}

  public List<TableOne> getList() {
		return tableOneRepository.findAll();
	}

  public void deleteById(Integer Id) {
    tableOneRepository.deleteById(Id);
  }

  public TableOne updateById(Integer id, TableOne tableOne) {
    return tableOneRepository.save(tableOne);
  }

}
