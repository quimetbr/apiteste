package com.example.apisimples.apisimples.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableOneDTO {

    private Integer tableId;
	private String  tableField1;
	private String  tableField2;

}
