import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class UrnaEletronica {

    public static void main(String[] args) throws IOException {
        Loader loader = new Loader(args);

        ServerSocket servidor = null;
        Socket socket = null;

        try {
            servidor = new ServerSocket(loader.getPorta());
            System.out.print("\033[H\033[2J");
            System.out.println("Este processo foi aberto na porta 7564.");
        } catch(IOException exception) {
            exception.printStackTrace();
        }

        Timer timer = new Timer();
        timer.schedule(new Parciais(), 0, loader.getTemporizador());

        while(true) {
            try {
                socket = servidor.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            new Requisicao(socket).start();
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
