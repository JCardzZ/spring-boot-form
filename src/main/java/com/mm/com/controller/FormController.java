package com.mm.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mm.com.models.Usuario;
import com.mm.com.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Manuel");
		usuario.setApellido("Cardoza");
		usuario.setIdentificador("123.456.789.000K");
		model.addAttribute("titulo", "Resultado Formulario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
//		validator.validate(usuario, result);
		
		model.addAttribute("titulo", "Resultado Formulario");

		if (result.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * 
			 * result.getFieldErrors().forEach(err -> { errores.put(err.getField(),
			 * "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())
			 * )); }); model.addAttribute("error", errores);
			 */
			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();

		return "resultado";
	}
}
