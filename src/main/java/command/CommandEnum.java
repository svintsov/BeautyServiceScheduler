package command;

import command.action.CreateVisitCommand;
import command.action.DeleteVisitCommand;
import command.action.FinishVisitCommand;
import command.action.LoginCommand;
import command.action.LogoutCommand;
import command.action.ReserveCommand;
import command.action.SearchVisitCommand;
import command.action.SetLocaleCommand;
import command.action.SignUpCommand;
import command.page.AddingFormCommand;
import command.page.AdminPageCommand;
import command.page.CustomerPageCommand;
import command.page.MasterPageCommand;
import command.page.RegistrationFormCommand;
import command.page.SearchResultPageCommand;
import command.page.SearchingFormCommand;

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
  ADMINPAGE{
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
  SEARCH_RESULT_PAGE{
    {
      this.command = new SearchResultPageCommand();
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