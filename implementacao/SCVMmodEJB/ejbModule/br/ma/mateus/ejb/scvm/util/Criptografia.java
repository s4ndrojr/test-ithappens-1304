package br.ma.mateus.ejb.scvm.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder;


/**
 * Essa classe tem o papel de fornecer ao sistema servicos de criptografia da
 * biblioteca java.security
 */
public final class Criptografia {
    //private static Criptografia instance;
	
    /**
     * Construtor da classe ServicosCriptografia
     */
    private Criptografia() {
    }

    public static synchronized String encriptarSenha(String plaintext) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA");

        } catch (NoSuchAlgorithmException e) {
        }
        
        try {
        	if(plaintext != null) {
        		md.update(plaintext.getBytes("UTF-8"));
        	}
        } catch (UnsupportedEncodingException e) {
        }

        byte raw[] = md.digest();

		String hash = null;
		try {
			hash = Base64Encoder.encode(raw);
			System.out.println("HASH:" + hash);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return hash;
    }

    public static void main(String[] args) {
    	
		System.out.println(Criptografia.encriptarSenha("admin"));
	}
}