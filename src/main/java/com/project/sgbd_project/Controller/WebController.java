package com.project.sgbd_project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The Web Controller that handles HTTP requests for the web application.
 * This controller is responsible for managing the requests made by the user
 * through the web interface. It acts as the entry point for processing web
 * requests and returns the appropriate views (HTML templates) in response.
 * In this example, the controller defines a method to handle the root ("/")
 * URL, which will return a view named "index". The `index` view will be
 * resolved to an HTML template (e.g., `src/main/resources/templates/index.html`).
 */
@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";  // This will automatically map to src/main/resources/templates/index.html
    }
}