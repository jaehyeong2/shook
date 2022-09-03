package jjfactory.shook.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiPagingRes<T> {
    private PagingRes<T> result;
}
