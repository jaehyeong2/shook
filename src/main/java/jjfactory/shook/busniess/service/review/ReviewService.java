package jjfactory.shook.busniess.service.review;


import jjfactory.shook.busniess.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public String create(){
        return "y";
    }

    public String delete(){
        return "y";
    }

    public String update(){
        return "y";
    }
}
