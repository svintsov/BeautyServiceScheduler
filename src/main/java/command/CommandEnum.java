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
  };
  Command command;

  public Command getCurrentCommand() {
    return command;
  }
}