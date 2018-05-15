package com.project.course.controller.commands;


import com.project.course.controller.commands.impl.admin.OrderCommand;
import com.project.course.controller.commands.impl.common.SwitchOrdersCommand;
import com.project.course.controller.commands.impl.common.*;
import com.project.course.controller.commands.impl.employee.DoOrderCommand;
import com.project.course.controller.commands.impl.member.AddCommentCommand;
import com.project.course.controller.commands.impl.member.AddOrderCommand;

/**
 * Created by kleba on 07.05.2018.
 */
public enum CommandEnum {

    LOGIN{
        {
            this.command= new AuthenticationCommand();
        }
    },
    REG{
        {
            this.command = new RegistryCommand();
        }

    },
    CHANGELANG{
        {
          this.command = new ChangeLanguageCommand();
        }
    },
    DEFAULT{
        {
            this.command= new DefaultPageCommand();
        }
    },
    LOGOUT{
        {
            this.command= new LogoutCommand();
        }
    },
    CHANGEORDER{
        {
            this.command = new SwitchOrdersCommand();
        }
    },
    GETDATA{
        {
         this.command = new GetDataCommand();
        }
    },
    ADDORDER{
        {
          this.command = new AddOrderCommand();
        }
    },
    DOORDER{
        {
            this.command = new DoOrderCommand();
        }
    },
    ADDCOMMENT{
        {
            this.command = new AddCommentCommand();
        }

    },
    CRUDORDER{
        {
            this.command= new OrderCommand();
        }
    },
    ORDERINFO{
        {
            this.command = new OrderInfoCommand();
        }
    },
    CHANGECOMMENTS{
        {
            this.command = new ChangeCommentsCommand();
        }
}   ,
    INVALID{
        {
            this.command = new InvalidCommand();
        }
    } ;
    Command command;

    public Command getCommand() {
        return command;
    }

}
