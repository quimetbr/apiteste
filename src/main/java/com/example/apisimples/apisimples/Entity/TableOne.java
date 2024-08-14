package com.example.apisimples.apisimples.Entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Component
@Entity
@Table(name="TableOne")
@Data
public class TableOne {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tableId;
	private String  tableField1;
	private String  tableField2;

}
