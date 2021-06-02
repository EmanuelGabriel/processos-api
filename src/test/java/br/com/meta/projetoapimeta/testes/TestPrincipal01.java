package br.com.meta.projetoapimeta.testes;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPrincipal01 {
	
	private static final String URL_BASE = "http://inter03.tse.jus.br/sadpPush/Pesquisa.do";

	public static void main(String[] args) {

		try {

			Connection connection = Jsoup.connect(URL_BASE);
			connection.method(Connection.Method.GET);
			connection.data("comboTribunal", "pi");
			connection.data("acao", "pesquisarNumProcesso");
			connection.data("siglaTribunal", "pi");
			connection.data("nomeTribunal", "TRE-PI");
			connection.data("tipoPesquisa", "divProcesso");
			connection.data("numProcesso", "12628");
			connection.data("numUnicoSequencial", "");
			connection.data("numUnicoAno", "");
			connection.data("numUnicoOrigem", "");
			connection.data("numProtocolo", "");
			connection.data("tipoConsultaProtocolo", "sa");
			connection.data("nomeParte", "");
			connection.data("tipoConsultaNomeParte", "in");
			connection.data("nomeAdvogado", "");
			connection.data("tipoConsultaNomeAdvogado", "in");
			connection.data("numOAB", "");
			connection.data("ufOAB", "AC");
			connection.data("numOrigem", "");
			connection.data("anoEleicao", "");
			connection.data("nomeMunicipio", "");
			Connection.Response response = connection.execute();
			Document document = response.parse();
			log.info(document.html());

		} catch (IOException ex) {
			log.info(ex.getMessage());
		}

	}

}
