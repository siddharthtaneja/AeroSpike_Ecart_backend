package com.aerospike.aerospikecaching.Service;

import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public Items edit(Long price, Items items) {
        Items newItem = new Items();
        newItem.setCategory(items.getCategory());
        newItem.setImage(items.getImage());
        newItem.setName(items.getName());
        newItem.setProductId(items.getProductId());
        newItem.setPrice(items.getPrice());
        newItem.setDiscountprice(price);
        return itemRepository.saveAndFlush(newItem);
    }
}
