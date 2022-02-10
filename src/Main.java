import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ProcessManager processManager = new ProcessManager();
        ArrayList<String> p = new ArrayList<>();
        p.add("Discord.exe");
        p.add("explorer.exe");
        p.add("idea64.exe");
        processManager.addToIgnoreList(p);
        ArrayList<String> p2 = new ArrayList<>();
        p2.add("chrome.exe");
        processManager.addToIgnoreList(p2);
        processManager.removeFromIgnoreListByName("chrome.exe");
        processManager.killProcesses();

    }
}