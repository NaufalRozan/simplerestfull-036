/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplerestfull.simplerestfull;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rozan
 */
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    
    //Method hapus data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id")String id){
        //Kondisi ID tidak ada ketika dihapus
        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        productRepo.remove(id);
        return new ResponseEntity<>("Produk Berhasil di Hapus", HttpStatus.OK);
    }
    
    //Method edit data
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        //Kondisi ID tidak ada ketika diedit
        if(!productRepo.containsKey(id))throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Produk Berhasil di Update", HttpStatus.OK);
    }
    
    //Method tambah data
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productRepo.put(product.getId(), product);
        //Kondisi ID sudah ada ketika data ditambah
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("ID Produk Sudah Ada", HttpStatus.OK);
        }
        //KOndisi ID kosong ketika tambah data
        else if (productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("ID Produk Harus Diisi", HttpStatus.OK);
        }
        return new ResponseEntity<>("Produk Berhasil Dibuat", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
