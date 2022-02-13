package it.uninsubria.campusar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String root(Model model) {
		return index(model);
	}

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/ar-view")
	public String arView(Model model){
		return "AugmentedReality";
	}

	@GetMapping("/map")
	public String map(Model model){
		return "map";
	}
}