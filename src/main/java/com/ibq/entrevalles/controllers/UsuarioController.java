package com.ibq.entrevalles.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibq.entrevalles.model.LoginRequest;
import com.ibq.entrevalles.model.NuevaContrasenya;
import com.ibq.entrevalles.model.Reserva;
import com.ibq.entrevalles.model.Usuario;
import com.ibq.entrevalles.repository.UsuarioRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Usuario>
	login(@RequestBody LoginRequest request) {
		Usuario usuario = this.usuarioRepository.findByEmailAndContrasenya(request.getEmail(), request.getContrasenya());
		if(usuario != null) {
			for(Reserva r: usuario.getReservas()) {
				r.setExperiencia(null);
			}
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.status(401).body(null);
		}
	}
	
	@PostMapping("/registro")
	public @ResponseBody ResponseEntity<Usuario>
	registro(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(this.usuarioRepository.save(usuario));
	}
	
	@PostMapping("/nueva")
    public ResponseEntity<Usuario> establecer(@RequestBody NuevaContrasenya usuario) {
        // Buscar el usuario por su nombre de usuario
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente != null) {
            // Usuario encontrado, permitir modificar la contraseña
            usuarioExistente.setContrasenya(usuario.getContrasenya());
            Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
            sendSimpleMessage(usuario.getEmail(),"NUEVA CONTRASEÑA ENTREVALLES Y MONTAÑAS","Se ha actualizado su contraseña correctamente");
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            // Usuario no encontrado, devolver un error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
	 public void sendSimpleMessage(
		      String to, String subject, String text) {
		 String result =null;
		    MimeMessage message =emailSender.createMimeMessage();
		    try {

		        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		        ClassPathResource resource = new ClassPathResource("static/cambioContrasenya/index.html");
		        byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		        String htmlMsg = new String(fileData, "UTF-8");
		        helper.setTo(to);
		        helper.setFrom("entrevallesymontanias@gmail.com");
		        helper.setSubject(subject);
	            helper.setText(htmlMsg, true);
		        // Configurar la imagen incrustada
	            ClassPathResource image = new ClassPathResource("static/cambioContrasenya/images/image-1.png");
	            helper.addInline("image-1.png", image);
	            ClassPathResource image2 = new ClassPathResource("static/cambioContrasenya/images/image-2.png");
	            helper.addInline("image-2.png", image2);
	            ClassPathResource image3 = new ClassPathResource("static/cambioContrasenya/images/image-3.png");
	            helper.addInline("image-3.png", image3);
	            ClassPathResource image4 = new ClassPathResource("static/cambioContrasenya/images/image-4.png");
	            helper.addInline("image-4.png", image4);
	            ClassPathResource image5 = new ClassPathResource("static/cambioContrasenya/images/image-5.gif");
	            helper.addInline("image-5.gif", image5);
		        result="success";
		        emailSender.send(message);
		    } catch (MessagingException | IOException e) {
		        throw new MailParseException(e);
		    }finally {
		        if(result !="success"){
		            result="fail";
		        }
		    }
		    }
}