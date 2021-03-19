package com.aerospike.aerospikecaching.Controller;

import com.aerospike.aerospikecaching.Model.Reviews;
import com.aerospike.aerospikecaching.Repository.ReviewRepository;
import com.aerospike.aerospikecaching.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @GetMapping("/post/{id}/{str}")
    public String postComment(Principal principal, @PathVariable(value = "id") Long id, @PathVariable(value = "str")String str){
        return reviewService.postReview(principal,id,str);
    }
    @GetMapping("/get/{id}")
    public List<Reviews> getReviews(@PathVariable(value = "id")Long id){
        return reviewService.findAllReviewsByUser(id);
    }
    @GetMapping("getAllReviews/{id}")
    public List<Reviews> getAll(@PathVariable(value = "id")Long id){
        return reviewRepository.findAll();
    }
    @PostMapping("/edit/{id}/{str}")
    public Reviews editReview(@PathVariable(value = "id")Long id,@RequestBody Reviews reviews,Principal principal,@PathVariable(value = "str")String str){
        return  reviewService.edit(reviews,id,principal,str);
    }
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable(value = "id")Long id){
        return reviewService.deleteReview(id);
    }
    @GetMapping("/getReview/{id}")
    public Reviews findReview(@PathVariable(value = "id")Long id){
        return reviewRepository.findByProductId(id);
    }

}
