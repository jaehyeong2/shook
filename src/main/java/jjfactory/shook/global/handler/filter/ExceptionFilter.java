package jjfactory.shook.global.handler.filter;
import com.google.gson.Gson;
import jjfactory.shook.global.handler.ex.ErrorCode;
import jjfactory.shook.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            filterChain.doFilter(request,response);
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response,Throwable ex){
        response.setStatus(status.value());
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.HANDLE_ACCESS_DENIED);
        try{
            Gson gson = new Gson();
            String json = gson.toJson(errorResponse);
            //String json = errorResponse.convertToJson();
            System.out.println(json);
            response.getWriter().write(json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}