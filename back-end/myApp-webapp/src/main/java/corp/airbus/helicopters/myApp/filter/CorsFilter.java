package corp.airbus.helicopters.myApp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Allow cross domain operations, ie for development environment.
 */
public class CorsFilter extends OncePerRequestFilter {

    /* (non-Javadoc)
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "http://localhost:9000");
        response.addHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE");
        response.addHeader("Access-Control-Allow-Headers",
                "X-Requested-With,Origin,Content-Type, Accept, SPRING_USER_ID, TABLE_PAGINATED_TOTAL");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.addHeader("Access-Control-Expose-Headers", "TABLE_PAGINATED_TOTAL");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod())) {
            return;
        }
        filterChain.doFilter(request, response);
    }
}
