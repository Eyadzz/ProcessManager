import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProcessManager {
    private String processLineInfo;
    private String[] cmd = {"cmd.exe", "/c", "tasklist | sort"};
    private BufferedReader processList;
    private ArrayList<String> ignoreList = new ArrayList<>();

    private BufferedReader getProcessList() throws IOException {
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return bufferedReader;
    }

    private String getExectuableProcess(BufferedReader processList) throws IOException {
        while (!processLineInfo.contains(".exe"))
            processLineInfo = processList.readLine();
        return processLineInfo;
    }

    public void killProcesses() throws IOException {
        processList = getProcessList();
        processLineInfo = processList.readLine();
        while (true) {
            processLineInfo = getExectuableProcess(processList);
            String programName = processLineInfo.substring(0, processLineInfo.indexOf(".exe"));
            ArrayList<String> singleProgramProcesses = getSingleProgramProcesses(programName);
            executeTaskKill(singleProgramProcesses);
            singleProgramProcesses.clear();
            if (processLineInfo == null)
                break;
        }
    }

    private ArrayList<String> getSingleProgramProcesses(String programName) throws IOException {
        boolean isService = false;
        ArrayList<String> singleProgramProcesses = new ArrayList<>();
        while (processLineInfo.contains(programName)) {
            singleProgramProcesses.add(processLineInfo);
            if (processLineInfo.contains("Services")) {
                isService = true;
            }
            processLineInfo = processList.readLine();
            if (processLineInfo == null)
                return singleProgramProcesses;
        }
        if(isService)
        {
            singleProgramProcesses.clear();
        }
        return singleProgramProcesses;
    }

    private void executeTaskKill(ArrayList<String> singleProgramProcesses) throws IOException {
        for (int i = 0; i < singleProgramProcesses.size(); i++) {
            String process = singleProgramProcesses.get(i).substring(0, singleProgramProcesses.get(i).indexOf(".exe")) + ".exe";
            Runtime rt = Runtime.getRuntime();
            if(!checkIgnoreList(process)) {
                rt.exec("taskkill /f /im " + process);
                System.out.println("KIlled " + process);
            }
            else {
                break;
            }
        }
    }

    private boolean checkIgnoreList(String processName)
    {
        return ignoreList.contains(processName);
    }

    public void addToIgnoreList(ArrayList<String> programNames)
    {
        ignoreList.addAll(programNames);
    }
    public void clearIgnoreList(){
        ignoreList.clear();
    }
    public void removeFromIgnoreListByName(String programName){
        ignoreList.remove(new String(programName));
    }
}
/*

1- UI
2- Documentation
3- Readme
4- Restrict user usage
5- Link tool with website
6- Make tool unkillable
 */