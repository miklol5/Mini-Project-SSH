package client;

import com.jcraft.jsch.*;
import java.io.*;

// โปรแกรมสำหรับเชื่อมต่อ SSH ไปยัง Server และรันคำสั่งผ่านไลบรารี JSch
public class SSHClient {

    public static void main(String[] args) {

        // ตรวจสอบว่ารับ argument ครบ 4 ค่า (host, user, password, command) หรือไม่
        if (args.length < 4) {
            System.out.println("Usage: java client.SSHClient <host> <user> <password> <command>");
            return;
        }

        // กำหนดค่าที่รับมาจาก command line
        String host = args[0];
        String user = args[1];
        String password = args[2];
        String command = args[3];

        try {

            // สร้างออบเจ็กต์ JSch และเตรียม Session
            JSch jsch = new JSch();

            // เชื่อมต่อไปยัง Server พอร์ต 2222
            Session session = jsch.getSession(user, host, 2222);
            session.setPassword(password);

            // ปิดการตรวจสอบ Host Key
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(); // เริ่มการเชื่อมต่อ

            // เปิด Channel แบบ exec เพื่อรันคำสั่ง
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            channel.setInputStream(null);
            channel.setErrStream(System.err);

            // รับผลลัพธ์จากคำสั่ง
            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];

            // วนลูปอ่านผลลัพธ์จาก Server
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
                Thread.sleep(100); // หน่วงเวลาเล็กน้อยก่อนตรวจสอบใหม่
            }

            // ปิดการเชื่อมต่อ
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {

            // แสดงข้อความเมื่อเกิดข้อผิดพลาด
            System.out.println("Error: " + e.getMessage());
        }

    }

}