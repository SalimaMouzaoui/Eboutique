package dz.elit.eboutique.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Controlleur {

	private static final Logger logger = LoggerFactory.getLogger(Controlleur.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home (Locale locale, Model model){
		logger.info("Welcome ", locale);
		Date date = new Date();
		DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formatedDate = dateformat.format(date);
		model.addAttribute("serverTime" , formatedDate);
		return "home";
	}
	
}
