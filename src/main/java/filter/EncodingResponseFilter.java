package filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingResponseFilter implements Filter {

  public void destroy() {
  }

  public void doFilter(ServletRequest req,
      ServletResponse resp,
      FilterChain chain)

      throws ServletException, IOException {

    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
  }
}