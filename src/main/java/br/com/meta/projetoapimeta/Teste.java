package br.com.meta.projetoapimeta;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author emanuel.sousa
 *
 */

public class Teste {

	public static void main(String[] args) {

		Map<String, String> usuarioMap = new HashMap<>();
		usuarioMap.entrySet();
		
		final String chaveSecreta = "Em@nuel2021@#2021@#AquiNuncaVaiSerSeguroNaInternet#2021#PTLadrao#"; // Secret para verificação da assinatura do JWT
		final String tokenContent = "meuemail@email.com"; // Conteúdo a ser incluído no JWT
		final Long dataExpiracaoToken = 7L;
		
		// O token irá expirar em 7 dias
		final Date expirationDate = Date.from(LocalDateTime.now().plusDays(dataExpiracaoToken).atZone(ZoneOffset.systemDefault()).toInstant()); 
		
		String token = Jwts.builder().setSubject(tokenContent).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, chaveSecreta).compact();
		
		// Imprime o token
		System.out.println(token); 

		String usuario = Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
		
		// Imprime meuemail@email.com
		System.out.println("PAYLOAD:".concat(usuario)); 

	}

}
