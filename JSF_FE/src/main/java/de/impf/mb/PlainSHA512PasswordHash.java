package de.impf.mb;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.PasswordHash;

@ApplicationScoped
public class PlainSHA512PasswordHash implements PasswordHash {

	@Override
	public String generate(char[] password) {
		String generatedPassword = null;
		String passwordToHash = new String(password);
		String salt = "hex";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@Override
	public boolean verify(char[] password, String hashedPassword) {
		System.out.println(new String(password) +" "+ hashedPassword);
		return hashedPassword != null && hashedPassword.equals(generate(password));
	}

}
