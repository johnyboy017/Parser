import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

		File file = new File("teste.xml");
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			Stack pilha = new Stack();
			ArrayList<String> lista = new ArrayList<String>();
			String st, novoTexto;
			int contador = 0;
			lista.add("{");
			String[] vetor;
			String col = "";
			State estAnterior = new State();

			while ((st = br.readLine()) != null) {
				vetor = st.split("<");

				if(vetor.length == 2) {										
					if(pilha.retornaTopo() != null && ("/" + pilha.retornaTopo().getValue()).equals(vetor[1].substring(0, vetor[1].length() - 1))) {						
						if(contador != 0 && !estAnterior.getValue().equals(pilha.retornaTopo().getValue()))
							col = "]";
						estAnterior = pilha.retornaTopo();
						pilha.desempilha();
						if(lista.get(lista.size() - 1).contains(",")) {
							novoTexto = lista.get(lista.size() - 1).replace(",", "");
							lista.set(lista.size() - 1, novoTexto);
						}
						if(col.equals("]")) {
							lista.add(col);
							col = "";
							contador = 0;
						}							
						lista.add("}");
					}	

					else {
						if(vetor[1].substring(0, vetor[1].length() - 1).equals(estAnterior.getValue())) {
							int ind = lista.indexOf("\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": ");
							if(ind != -1)
								lista.set(ind, "\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": [");
							contador++;
							novoTexto = lista.get(lista.size() - 1);
							lista.set(lista.size() - 1, novoTexto.concat(","));
						}
						else {
							lista.add("\"" + vetor[1].substring(0, vetor[1].length() - 1) + "\": ");
							contador = 0;
						}							
						pilha.empilha(vetor[1].substring(0, vetor[1].length() - 1));						
						lista.add("{");
					}
				}

				else {
					lista.add("\"" + vetor[1].split(">")[0] + "\": " + "\"" + vetor[1].split(">")[1] + "\",");
				}								
			}
			lista.add("}");
			if(pilha.Tamanho() != 0)
				System.out.println("Erro!");
			else
				salvarArquivo("teste.json", lista);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
	}
	
	private static void salvarArquivo(String caminho, ArrayList<String> lista) {
		BufferedWriter bw = null;
		try {
			File writeFile = new File(caminho);
			  writeFile.createNewFile();
			  bw = new BufferedWriter(new FileWriter(writeFile));
			  for(String texto : lista) {
				  bw.write(texto);
				  bw.newLine();
			  }
		}
		catch(Exception e){}
    }
}
