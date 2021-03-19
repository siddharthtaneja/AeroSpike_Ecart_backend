package com.aerospike.aerospikecaching.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Repository.ItemRepository;
import com.aerospike.aerospikecaching.Repository.SimpleJpaRepository;
import com.aerospike.aerospikecaching.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    SimpleJpaRepository Simple;
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepositoryClass;
    @GetMapping("/products")
    @Cacheable(value = "test", key = "56")
    public List<Items> getAllItems()
    {
        System.out.println("Avx");
        return Simple.findAll();
    }
    @PostMapping("/notes")
    @CachePut(value = "test" , key = "56")
    public  List<Items> createNote(@RequestBody Items item){
          Simple.save(item);
          return Simple.findAll();
    }
    @GetMapping("/par/{id}")
    @Cacheable(value = "test" , key = "#productId")
    public Items getDetailsById(@PathVariable(value="id")Long productId)
    {
        System.out.println("Im g");
        return itemRepositoryClass.findByProductId(productId);
    }
    @GetMapping("/fish/{name}")
    public List<Items> getDetailsByname(@PathVariable(value="name")String name)
    {
        return Simple.findByName(name);
    }
    @GetMapping("/cat/{cat}")
    public List<Items> getDetailsByCat(@PathVariable(value="cat")String category)
    {
        return Simple.findByCategory(category);
    }
    @GetMapping("/{cat}/{price1}/{price2}")
    public List<Items> getDetailsByCatAndPrice(@PathVariable(value ="cat")String category,@PathVariable(value = "price1")Double price1,@PathVariable(value = "price2")Double price2){
        return Simple.findByCategoryAndPriceBetween(category,price1,price2);
    }
    @GetMapping("/{price1}/{price2}")
    public List<Items> getByPriceBetween(@PathVariable(value="price1")Double price1,@PathVariable(value ="price2")Double price2){
        return Simple.findByPriceBetween(price1,price2);
    }
    @PostMapping("/edit/{price}")
    public Items editDetails(@PathVariable(value = "price")Long price,@RequestBody Items items){
        return itemService.edit(price,items);
    }
    @GetMapping("/delete/{id}")
    @Caching(evict = {@CacheEvict(value = "test",key = "#id"),@CacheEvict(value = "test",key = "56")})
    public List<Items> deleteitems(@PathVariable(value = "id") Long id){
        Simple.deleteById(id);
        return Simple.findAll();
    }
}
