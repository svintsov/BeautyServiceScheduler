package command.action;

import bundle.MessageManager;
import command.Redirector;
import entity.Role;
import service.VisitService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Sending review for specific visit
 */
public class SendReviewCommand implements command.Command {

    private static final String ATTRIBUTE_NAME_ID_VISIT ="idvisit";
    private static final String PARAM_NAME_REVIEW ="review";

    /**
     * Executes command
     * @param request
     * @return Redirection to customer's main page
     */
    @Override
    public String execute(HttpServletRequest request) {
        final VisitService visitService = new VisitService();
        final int idVisit = (Integer)request.getSession().getAttribute(ATTRIBUTE_NAME_ID_VISIT);

        try {
            visitService.writeReviewForVisit(idVisit,request.getParameter(PARAM_NAME_REVIEW));
        } catch (SQLException e) {
            request.setAttribute("errorMessage", MessageManager.getProperty(e.getMessage()));
        }

        return Redirector.getMenu(Role.CUSTOMER,request);
    }
}
