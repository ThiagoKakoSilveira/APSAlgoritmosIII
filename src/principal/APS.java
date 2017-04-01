package principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APS {
	static List<char[][]> matrizesDoArquivo = new ArrayList<>();
	static List<char[][]> matrizesClonadasAlterando0 = new ArrayList<>();
	static List<char[][]> matrizesClonadasAlterando1 = new ArrayList<>();
	static List<Integer> listaColunas = new ArrayList<>();
	
	public static void main(String[] args) {
		for (int i = 1; i < 5; i++) {
			String numeroArquivo = String.valueOf(i);
			matrizesDoArquivo.add(entregaMatrizDoArquivo(numeroArquivo));			
		}
		clonarMatrizes();
		alteraMatrizes();
		mostraMatrizes();
	}

	private static void mostraMatrizes() {
		mostraMatriz("Matrizes Originais", matrizesDoArquivo);
		mostraMatriz("Matriz alterando o 0", matrizesClonadasAlterando0);
		mostraMatriz("Matriz alterando o 1", matrizesClonadasAlterando1);
	}

	private static void mostraMatriz(String titulo, List<char[][]> matrizes) {
		int cont = 0;
		String numeroMatriz = "1";
		System.out.println(titulo+"\n");
		for (char[][] matriz : matrizes) {
			int coluna = listaColunas.get(cont);
			System.out.println("Matriz do aquivo "+ numeroMatriz+"\n");
			for (int i = 0; i < matriz.length; i++) {
				StringBuilder apresentacao = new StringBuilder("|");			
				for (int j = 0; j < coluna; j++) {
					apresentacao.append(matriz[i][j]);
					apresentacao.append('|');
				}
				apresentacao.append("\n");
				System.out.print(apresentacao.toString());
			}
			System.out.println();
			cont++;
			numeroMatriz = String.valueOf((cont + 1));
		}
	}

	private static void alteraMatrizes() {		
		alteraO0(matrizesClonadasAlterando0);
		alteraO1(matrizesClonadasAlterando1);
	}

	private static void alteraO1(List<char[][]> matrizes) {
		int cont = 0;
		for (char[][] matrizAlt1 : matrizes) {
			int coluna = listaColunas.get(cont);
			for (int i = 0; i < matrizAlt1.length; i++) {
				for (int j = 0; j < coluna; j++) {					
					if (matrizAlt1[i][j] == '1') {
						matrizAlt1[i][j] = '2';
					}
				}
			}
			cont++;
		}
	}

	private static void alteraO0(List<char[][]> matrizes) {
		int cont = 0;
		for (char[][] matrizAlt0 : matrizes) {
			int coluna = listaColunas.get(cont);
			for (int i = 0; i < matrizAlt0.length; i++) {
				for (int j = 0; j < coluna; j++) {					
					if (matrizAlt0[i][j] == '0') {
						matrizAlt0[i][j] = '2';
					}
				}
			}
			cont++;
		}
	}

	private static void clonarMatrizes() {
		int cont = 0;
		for (char[][] matriz : matrizesDoArquivo) {
			int coluna = listaColunas.get(cont);
			char[][] matrizAuxiliar = new char[matriz.length][coluna];
			char[][] matrizAuxiliar2 = new char[matriz.length][coluna];
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < coluna; j++) {
					matrizAuxiliar[i][j] = matriz[i][j];
					matrizAuxiliar2[i][j] = matriz[i][j];
				}				
			}
			matrizesClonadasAlterando0.add(matrizAuxiliar);
			matrizesClonadasAlterando1.add(matrizAuxiliar2);
			cont++;
		}		
	}

	private static char[][] entregaMatrizDoArquivo(String numeroArquivo) {
		
		int qntColuna = 0;
		int qntLinha = 0;
		
		char[][] matrizArquivo = new char[qntLinha][qntColuna];
		
		try (BufferedReader reader = new BufferedReader(
				new FileReader("resources/example_"+numeroArquivo+".txt"))) {

			String[] colin = reader.readLine().split(" ");

			qntColuna = Integer.parseInt(colin[0]);
			listaColunas.add(qntColuna);
			qntLinha = Integer.parseInt(colin[1]);
					
			matrizArquivo = new char[qntLinha][qntColuna];
			
			for (int i = 0; i < matrizArquivo.length; i++) {
				String linha = reader.readLine();
				for (int j = 0; j < qntColuna; j++) {
					matrizArquivo[i][j] = linha.charAt(j);							
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Não encontrou o arquivo!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Não conseguiu ler o arquivo!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Erro de parse");
			e.printStackTrace();
		}
		return matrizArquivo;
	}
}
