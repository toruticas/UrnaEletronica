import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class UrnaEletronica {

    public static void main(String[] args) throws IOException {
        Loader loader = new Loader(args);

        ServerSocket servidor = null;
        Socket socket = null;
        Integer porta = loader.getPorta();

        try {
            servidor = new ServerSocket(porta);
            System.out.print("\033[H\033[2J");
            System.out.format("Este processo foi aberto na porta %d.", porta);
        } catch(IOException exception) {
            exception.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new Parciais(), 0, loader.getTemporizador());

        Path logPath = Paths.get("log/requests.txt");
        // Create file if it doesn't exist
        if (! Files.exists(logPath)) {
            Files.createDirectories(logPath.getParent());
            Files.createFile(logPath);
        }
        BufferedWriter log = Files.newBufferedWriter(
            logPath, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        while(true) {
            try {
                socket = servidor.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            new Requisicao(socket, log).start();
        }
    }
}

class Parciais extends TimerTask {
    public void run() {
        Colegiado colegiado = Colegiado.getInstance();
        System.out.println("\033[3;0H" + new Date());
        colegiado.printarCandidatos();
    }
}
