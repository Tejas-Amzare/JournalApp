package persnal.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "I am good...:)";
    }
}

