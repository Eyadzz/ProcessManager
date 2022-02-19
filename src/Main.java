import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessManager processManager = new ProcessManager();
        ArrayList<String> p = new ArrayList<>();
        p.add("Discord.exe");
        //p.add("explorer.exe");
        p.add("kill.exe");
        p.add("idea64.exe");
        p.add("sihost.exe");
        p.add("cmd.exe");
        p.add("java.exe");
        p.add("javaw.exe");
        p.add("ApplicationFrameHost.exe");
        p.add("dwm.exe");
        processManager.addToIgnoreList(p);
        ArrayList<String> p2 = new ArrayList<>();
        p2.add("chrome.exe");
        processManager.addToIgnoreList(p2);
        processManager.killProcesses();
        processManager.monitorActivity();
    }
}