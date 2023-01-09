package pe.memo.memoflashcardsbe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiagnosticsController {
    @Value("${appName}")
    private String appName;

    @Value("${environment}")
    private String environment;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", this.appName);
        model.addAttribute("environment", this.environment);
        return "health_check/index";
    }
}
