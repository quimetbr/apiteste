package com.example.apisimples.apisimples.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.apisimples.apisimples.DTO.APIError;
import com.example.apisimples.apisimples.DTO.DataReturn;
import com.example.apisimples.apisimples.DTO.TableOneDTO;
import com.example.apisimples.apisimples.Entity.TableOne;
import com.example.apisimples.apisimples.Service.TableOneService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

//API com endpoints retornando strings e json 
//Método Get simples para retornar em formato json o String recebido na entrada
//Uso do ResponseEntity para retorno resposta 
//Adicionado DTO APIError para retornar error de forma padronizada
//Adicionado Handling error no ResponseEntity
//Adicionado log.info
//Adicionar PUT, POST, DELETE com H2


@Slf4j
@RestController
public class ApiController {


    @Autowired
    TableOneService tableOneService;

    @Autowired
    TableOneDTO tableOneDTO;

    //Método Get simples para retornar em formato String o parâmetro de entrada
    @GetMapping("/hola")
    public String getHola(@RequestParam String param) {
        log.info("Dentro do método getHola!!! com parametro: "+param);
        return new String("Hola !!! em formato String e com o parâmetro de entrada: " + param);
    }
    
    @GetMapping("/hello")
    public ResponseEntity<Object> getHello(@RequestParam String param) {

        String errortext = "error";
    
        if (param.equals(errortext)) { 
            APIError error = new APIError(HttpStatus.BAD_REQUEST.value(),"retorno com error");
            System.out.println("Retorno com error " +param);
            log.info("Dentro do retorno com error");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);   
        };

        String errornull = "null";

		if (param.equals(errornull)) {
           System.out.println("Forçando null pointer");
           log.info("Dentro do forçar nullpointer por parâmetro");
			throw new NullPointerException();
        };

        System.out.println("Retorno com sucesso " +param);
        log.info("Retorno com sucesso "+param);
        DataReturn dataRetorno = new DataReturn(HttpStatus.OK.value(), "Parâmetro recebido foi: "+param);
        return new ResponseEntity<>(dataRetorno, HttpStatus.OK);
    }

    @PostMapping("/posttableone")
    public ResponseEntity<Object> postTableOne(@RequestBody TableOne tableOne) {
        
        return new ResponseEntity<>(tableOneService.postTableOne(tableOne), HttpStatus.CREATED);
        
    }

    @GetMapping("/getTableById")
    public ResponseEntity<Object> getTableById(@RequestParam Integer id) {
        //optional com o objeto esperado já que pode não retornar valor para esse id
        Optional<TableOne> retorno = tableOneService.getRegister(id);
        if (retorno.isEmpty()) {
            APIError error = new APIError(HttpStatus.NOT_FOUND.value(),"Id não encontrado");
            System.out.println("Id não encontrado: " +id);
            log.info("Dentro do retorno com error para id não encontrado: "+id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
        } else {
            return new ResponseEntity<>(retorno,HttpStatus.FOUND);    
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getList() {
        //List  com o objeto esperado já que é uma lista que pode ser vazia
        
        List<TableOne> retorno = tableOneService.getList();
        if (retorno.isEmpty()) {
            APIError error = new APIError(HttpStatus.NOT_FOUND.value(),"Não há registros");
            System.out.println("Não há registros que retornar na lista");
            log.info("Dentro do retorno com error para lista não encontrado: ");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
        } else {
            return new ResponseEntity<>(retorno,HttpStatus.FOUND);    
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestParam Integer id) {
        //antes de deletar verificamos se existe
        Optional<TableOne> retorno = tableOneService.getRegister(id);
        if(retorno.isEmpty()) {
            System.out.println("Id não encontrado: " +id);
            log.info("Dentro do retorno com error para id não encontrado: "+id);
            APIError error = new APIError(HttpStatus.NOT_FOUND.value(),"Id não encontrado, não teve como apagar");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
        } else {
            tableOneService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK); 
            
        }   
    }

    @PutMapping("/putById")
    public ResponseEntity<Object> putById(@RequestParam Integer id, @RequestBody TableOne tableOne) {
        //antes de alterar verificamos se existe
        log.info("Dentro do método put");
        Optional<TableOne> retorno = tableOneService.getRegister(id);
        
        TableOneDTO objeto = new TableOneDTO(1, "texto a", "texto b");

        System.out.println(objeto.toString());
        System.out.println("Texto dentro do objeto: "+objeto.getTableField2());
        objeto.setTableField2("Novo texto");
        System.out.println("Texto alterado dentro do objeto: "+objeto.getTableField2());
                
        if (retorno.isPresent()) {
            log.info("Existe, pode ser alterada: "+retorno.toString());
            TableOne updated = tableOneService.updateById(id, tableOne);
            return new ResponseEntity<>(updated, HttpStatus.OK); 

        } else {
            System.out.println("Id não encontrado para poder ser alterado: " +id);
            log.info("Dentro do retorno com error para id não encontrado: "+id);
            APIError error = new APIError(HttpStatus.NOT_FOUND.value(),"Id não encontrado, não teve como alterar");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
        }
    }
    
    //Controlar paginação 
    




}
