package jjfactory.shook.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagingRes<T> {

    private Long totalCount;
    private int totalPage;
    private int currentPage;
    private List<T> resultList;

    public PagingRes(Page<T> page) {
        this.resultList = page.getContent();
        this.totalCount = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.currentPage = page.getPageable().getPageNumber() + 1;
    }
}
