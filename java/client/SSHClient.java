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
            InputStream err = channel.getErrStream();

            channel.connect();

            ByteArrayOutputStream response = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;

            while ((read = in.read(buffer)) != -1) {
                response.write(buffer, 0, read);
            }

            while ((read = err.read(buffer)) != -1) {
                response.write(buffer, 0, read);
            }

            System.out.print(response.toString());

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}