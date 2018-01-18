package command;

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
  };
  Command command;

  public Command getCurrentCommand() {
    return command;
  }
}