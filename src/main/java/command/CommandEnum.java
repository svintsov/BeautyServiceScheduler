package command;

import command.action.*;
import command.page.*;

/**
 * Storage for all available commands
 */
public enum CommandEnum {
  LOGIN {
    {
      this.command = new LoginCommand();
    }
  },
  LOGOUT {
    {
      this.command = new LogoutCommand();
    }
  },
  REGISTRATIONFORM{
    {
      this.command = new RegistrationFormCommand();
    }
  },
  SIGN_UP{
    {
      this.command = new SignUpCommand();
    }
  },
  SETLOCALE{
    {
      this.command = new SetLocaleCommand();
    }
  },
  ADMINISTRATORPAGE {
    {
      this.command = new AdminPageCommand();
    }
  },
  MASTERPAGE{
    {
      this.command = new MasterPageCommand();
    }
  },
  CUSTOMERPAGE{
    {
      this.command = new CustomerPageCommand();
    }
  },
  DELETE_VISIT{
    {
      this.command = new DeleteVisitCommand();
    }
  },
  FINISH_VISIT{
    {
      this.command = new FinishVisitCommand();
    }
  },
  SEARCH_VISIT{
    {
      this.command = new SearchVisitCommand();
    }
  },
  RESERVE_VISIT{
    {
      this.command = new ReserveCommand();
    }
  },
  ADDING_FORM{
    {
      this.command = new AddingFormCommand();
    }
  },
  SEARCHING_FORM{
    {
      this.command = new SearchingFormCommand();
    }
  },
  REVIEW_FORM{
    {
      this.command = new ReviewFormCommand();
    }
  },
  WRITE_REVIEW{
    {
      this.command = new SendReviewCommand();
    }
  },
  CREATE_VISIT{
    {
      this.command = new CreateVisitCommand();
    }
  };
  Command command;

  public Command getCurrentCommand() {
    return command;
  }
}