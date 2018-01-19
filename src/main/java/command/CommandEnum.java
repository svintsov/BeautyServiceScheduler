package command;

import command.action.CreateVisitCommand;
import command.action.DeleteVisitCommand;
import command.action.FinishVisitCommand;
import command.action.LoginCommand;
import command.action.LogoutCommand;
import command.action.SetLocaleCommand;
import command.action.SignUpCommand;
import command.page.AddingFormCommand;
import command.page.AdminPageCommand;
import command.page.RegistrationFormCommand;

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
  ADDING_FORM{
    {
      this.command = new AddingFormCommand();
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