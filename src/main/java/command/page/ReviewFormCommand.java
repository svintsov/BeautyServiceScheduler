package command.page;

import bundle.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command redirects to review form
 */
public class ReviewFormCommand implements command.Command {

    private static final String PARAM_NAME_ID_VISIT ="idvisit";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(PARAM_NAME_ID_VISIT,Integer.valueOf(request.getParameter(PARAM_NAME_ID_VISIT)));
        return ConfigurationManager.getProperty("path.page.review");
    }
}
