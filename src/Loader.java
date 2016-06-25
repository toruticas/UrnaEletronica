import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Loader {
    private Integer porta; // entre 1024 ~ 65535
    private Integer temporizador; // em ms
    private String arquivoCandidatos; // nome do arquivo

    Loader(String[] args) {
        this.porta = 40008;
        this.temporizador = 250;
        this.arquivoCandidatos = new String("config/candidatos.xml");
        carregarCandidatos();
    }

    public Integer getPorta() {
        return porta;
    }

    public Integer getTemporizador() {
        return temporizador;
    }

    public void carregarCandidatos() {
        Colegiado colegiado = Colegiado.getInstance();
        try {
            File inputFile = new File(arquivoCandidatos);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("candidato");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nNode;
                    colegiado.putCandidato(new Candidato(
                        Integer.parseInt(el.getElementsByTagName("codigo").item(0).getTextContent()),
                        el.getElementsByTagName("nome").item(0).getTextContent(),
                        el.getElementsByTagName("partido").item(0).getTextContent(),
                        Integer.parseInt(el.getElementsByTagName("votos").item(0).getTextContent())
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
