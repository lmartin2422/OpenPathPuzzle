/**
 * Thanks for applying to OpenPath Products!
 * If you are reading this, then one or more of our team members have likely already had a great discussion with you.
 * The next steps in our interview process are contained within the (somewhat broken) code below.
 * Please fix up the compile and runtime errors and then run the program to reveal the next steps.
 * We hope you enjoy this little exercise and we look forward to further conversations.
 *
 * Sincerely,
 * Your friends at OpenPath
 */


import java.sql.SQLOutput;
import java.util.Vector;

//-----------------------------------------------------------------------------

interface BewilderOperation {
    public String operate(String str);
}

//-----------------------------------------------------------------------------

class InstructionSets {
    public final static String ValidationStr = "TEST";

    // Make OutStrings static
    public static String[] OutStrings = {
            "Hsfbu!xpsl!gjyjoh!uif!dpnqjmf!fsspst\"!!Lffq!efcvhhjoh!up!vodpwfs!uif!sfnbjojoh!nfttbhft\"",
            "Dpohsbuvmbujpot\"!!Xf!bsf!uisjmmfe!uibu!zpv!gjyfe!uijt!qpps!mjuumf!bqq(t!cvht!up!sfdfjwf!uijt!nfttbhf/",
            "Qmfbtf!gffm!gsff!up!bee!boz!puifs!jnqspwfnfout/",
            "Podf!dpnqmfuf-!qmfbtf!fnbjm!zpvs!dpef!up!jogpApqfoqbuiqspevdut/dpn!boe!dbmm!vt!bu!521/9:8/1517!up!mfu!vt!lopx!zpv!tpmwfe!ju/!",
            "Xf!bsf!mppljoh!gpsxbse!up!ubmljoh!xjui!zpv!bcpvu!tpnf!bnb{joh!dbsffs!pqqpsuvojujft\"!"
    };

    public static BewilderOperation ConfuseOperation = (String str) -> {
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            int ci = (int) c;
            char cn = (char) (ci + 1);
            sb.append((char) cn);
        }
        return sb.toString();
    };

    public static BewilderOperation ClarifyOperation = (String str) -> {
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            int ci = (int) c;
            sb.append((char) (ci - 1));
        }
        return sb.toString();
    };

    public static String GetString(BewilderOperation bewilderOperation, String str) {
        return bewilderOperation.operate(str);
    }
}


//-----------------------------------------------------------------------------

public class Puzzle implements Runnable {
    Vector<String> logMsgVec = new Vector<>();
    boolean running = false;
    Thread newThread;

    public Puzzle() {
        running = true;
        newThread = new Thread(this);
        newThread.start();
    }

    public void solvePuzzle() {
        String confusedTestStr = InstructionSets.GetString(InstructionSets.ConfuseOperation, InstructionSets.ValidationStr);
        String clarifiedTestStr = InstructionSets.GetString(InstructionSets.ClarifyOperation, confusedTestStr);
        if (!clarifiedTestStr.equals(InstructionSets.ValidationStr)) {
            log("Oh no! Could the Bewilder Operations be malfunctioning?");
        } else {
            // Reveal all instructions
            for (String str : InstructionSets.OutStrings) {
                log(InstructionSets.GetString(InstructionSets.ClarifyOperation, str));
            }
        }
        running = false;  // Signal the thread to stop running after all messages
    }

    private void log(String msg) {
        logMsgVec.add(msg);  // Add messages to the log vector
    }

    public void run() {
        while (running || logMsgVec.size() > 0) {
            if (logMsgVec.size() > 0) {
                String logMsg = logMsgVec.firstElement();
                logMsgVec.remove(logMsg);
                System.out.println("\n-> " + logMsg);  // Print log message
            }
            try {
                Thread.sleep(1000);  // 1-second delay between messages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("When working, the log messages will reveal next steps...");
        Puzzle puzzle = new Puzzle();
        puzzle.solvePuzzle();

        // Wait for the thread to finish processing logs
        try {
            puzzle.newThread.join();  // Main thread waits for the newThread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program finished.");
    }
}

