package client;

import com.jcraft.jsch.*;
import java.io.*;

public class SSHClient {

    public static void main(String[] args) {

        // ต้องมี 4 arguments
        if (args.length < 4) {
            System.out.println("Usage: java client.SSHClient <host> <user> <password> <command>");
            return;
        }

        String host = args[0];
        String user = args[1];
        String password = args[2];
        String command = args[3];

        try {

            JSch jsch = new JSch();

            Session session = jsch.getSession(user, host, 2222);
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            channel.setInputStream(null);
            channel.setErrStream(System.err);

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
                if (channel.isClosed()) {
                    if (in.available() > 0)
                        continue;
                    break;
                }
                Thread.sleep(100);
            }

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}