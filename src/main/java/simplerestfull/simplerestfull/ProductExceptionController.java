/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplerestfull.simplerestfull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author rozan
 */
@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler (value = ProductNotfoundException.class)
    public ResponseEntity<Object> exception(ProductNotfoundException exception){
        return new ResponseEntity<>("Product Tidak Ditemukan", HttpStatus.NOT_FOUND);
    }
}
