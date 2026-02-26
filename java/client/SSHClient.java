package client;

import com.jcraft.jsch.*;
import java.io.*;

public class SSHClient {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No command provided");
            return;
        }

        String host = "localhost";
        String user = "student";
        String password = "1234";

        if (args.length == 0) {
            System.out.println("No command");
            return;
        }

        String command = args[0];

        try {

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 2222);
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(command);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];

            while (true) {

                while (in.available() > 0) {

                    int i = in.read(tmp, 0, 1024);

                    if (i < 0)
                        break;

                    System.out.print(new String(tmp, 0, i));
                }

                if (channel.isClosed())
                    break;
            }

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}