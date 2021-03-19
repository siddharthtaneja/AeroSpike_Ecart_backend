package com.aerospike.aerospikecaching.Service;

import com.aerospike.aerospikecaching.Model.Items;
import com.aerospike.aerospikecaching.Model.Reviews;
import com.aerospike.aerospikecaching.Model.Users;
import com.aerospike.aerospikecaching.Repository.ItemRepository;
import com.aerospike.aerospikecaching.Repository.ReviewRepository;
import com.aerospike.aerospikecaching.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    public String postReview(Principal principal, Long id, String str) {
        System.out.print(principal.getName());
        Users MYUser = userRepository.findByEmail(principal.getName());
        Items items = itemRepository.findByProductId(id);
        Reviews reviews = new Reviews();
        reviews.setItems(items);
        reviews.setReview(str);
        reviews.setUsers(MYUser);
        reviewRepository.save(reviews);
        return "\"Review Added\"";
    }
    public String deleteReview(Long id){
        System.out.print("AVc");
        reviewRepository.deleteById(id);
        return "\"Review Deleted\"";
    }
    public List<Reviews> findAllReviewsByUser(Long id){
        Items items = itemRepository.findByProductId(id);
        return reviewRepository.findByItems(items);
    }
    public Reviews edit (Reviews reviews,Long id,Principal principal,String str){
        Reviews reviews1 = reviewRepository.findByProductId(id);
        System.out.print(reviews1.getItems());
        reviews.setUsers(reviews1.getUsers());
        reviews.setItems(reviews1.getItems());
        reviews.setReview(str);
        reviews.setProductId(reviews1.getProductId());
        reviewRepository.saveAndFlush(reviews);
        return  reviews;
    }
}
