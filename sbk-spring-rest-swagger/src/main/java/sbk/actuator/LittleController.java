package sbk.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LittleController
{
  @Autowired
  private CounterService counterService;

  @GetMapping(value = "/doStuff")
  public String doStuff() {
    counterService.increment("doStuffCallCount");
    return "counted";
  }
}
