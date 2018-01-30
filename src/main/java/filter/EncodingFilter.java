package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter for encoding pages with UTF-8
 */
public class EncodingFilter implements Filter {

  public void destroy() {
  }

  public void doFilter(ServletRequest req,
      ServletResponse resp,
      FilterChain chain)

      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
  }
}