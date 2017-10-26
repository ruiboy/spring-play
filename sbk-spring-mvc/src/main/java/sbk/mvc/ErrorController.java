package sbk.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController
{
  @GetMapping("/error")
  public HttpStatus error() {
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
