import java.io.*;
import java.net.*;
import java.util.Date;

public class Requisicao extends Thread {
    protected Socket socket;
    protected Integer opcode;
    protected String dataStream;
    protected InputStream input;
    protected DataOutputStream output;
    protected BufferedReader buffer;
    protected BufferedWriter log;

    Requisicao(Socket socket, BufferedWriter log) {
        this.socket = socket;
        this.opcode = 0;
        this.log = log;
    }

    public void run() {
        Date inicioRequisicao = new Date();

        input = null;
        output = null;
        buffer = null;

        try {
            input = socket.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(input));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }

        try {
            String line = buffer.readLine();
            if(line.equals("999")) {
                opcode = 999;
                processarListagem();
            } else if(opcode == 888 || line.equals("888")) {
                opcode = 888;
                processarUrna();
            } else {
                processarErro();
            }
            output.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Colegiado colegiado = Colegiado.getInstance();
        // colegiado.printarCandidatos();
        gerarLog(inicioRequisicao, socket.getRemoteSocketAddress(), opcode);
    }

    protected void processarListagem() throws IOException {
        Colegiado colegiado = Colegiado.getInstance();
        String outputData = colegiado.listarCandidatos();
        output.writeBytes("100\n" + outputData + "\n\r");
    }

    protected void processarUrna() throws IOException {
        Colegiado colegiado = Colegiado.getInstance();
        while(true) {
            try {
                String line = buffer.readLine();
                if(line == null || line.isEmpty()) {
                    break;
                } else {
                    String[] values = line.split("\\|", -1);
                    if(values.length != 2) {
                        output.writeBytes("001\nFormato está errado.\n\r");
                        return;
                    } else {
                        Integer codigo = Integer.parseInt(values[0]);
                        Integer votos = Integer.parseInt(values[1]);
                        colegiado.computarVotos(codigo, votos);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        output.writeBytes("101\nUrna Processada.\n\r");
    }

    protected void processarErro() throws IOException {
        output.writeBytes("000\nOpcode deve ser 999 ou 888.\n\r");
    }

    protected void gerarLog(Date inicio, SocketAddress socket, Integer opcode) {
        try {
            log.write("Started " + opcode + " for " + socket + " at " +
                inicio + " and Processed in " +
                ((new Date()).getTime() - inicio.getTime()) + "ms\n");
            // Flush ASAP so we will not loose log on a crash
            log.flush();
        } catch ( IOException e ) {
            System.out.println(e);
        }
    }
}
