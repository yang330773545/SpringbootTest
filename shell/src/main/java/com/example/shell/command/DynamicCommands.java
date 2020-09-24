package com.example.shell.command;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class DynamicCommands {
    private boolean connected;

    @ShellMethod("Connect to the server.")
    public void connect(String user, String password) {
        connected = true;
    }

    @ShellMethod("Download the nuclear codes.")
    // 手动指定
    @ShellMethodAvailability("availabilityCheck")
    public void download() {
    }

    @ShellMethod("Disconnect from the server.")
    public void disconnect() {
    }


    // 方法名自动匹配download https://docs.spring.io/spring-shell/docs/2.0.0.RELEASE/reference/htmlsingle/#help-command
    public Availability downloadAvailability() {
        return connected
                ? Availability.available()
                : Availability.unavailable("you are not connected");
    }

    // @ShellMethodAvailability({"download", "disconnect"}) // 亦可这样使用 属性的默认值为"*"，它用作匹配所有命令名称的特殊通配符
    public Availability availabilityCheck() {
        return connected
                ? Availability.available()
                : Availability.unavailable("you are not connected");
    }

}
