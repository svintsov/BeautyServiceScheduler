package command.action;

import command.Redirector;
import entity.Role;
import service.VisitService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SendReviewCommand implements command.Command {

    private static final String ATTRIBUTE_NAME_ID_VISIT ="idvisit";
    private static final String PARAM_NAME_REVIEW ="review";

    @Override
    public String execute(HttpServletRequest request) {
        final VisitService visitService = new VisitService();
        final int idVisit = (Integer)request.getSession().getAttribute(ATTRIBUTE_NAME_ID_VISIT);

        try {
            visitService.writeReviewForVisit(idVisit,request.getParameter(PARAM_NAME_REVIEW));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Redirector.getMenu(Role.CUSTOMER,request);
    }
}
