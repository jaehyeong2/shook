package jjfactory.shook.global.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyPageReq {
    private int page;
    private int size;

    public PageRequest of(){
        return PageRequest.of(page-1,size);
    }
}
