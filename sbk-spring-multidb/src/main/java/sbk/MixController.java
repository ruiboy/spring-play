package sbk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbk.db1.SystemPropertyService;
import sbk.db2.SuburbService;

@RequestMapping("mix")
@RestController
public class MixController
{

  @Autowired
  private SystemPropertyService systemPropertyService;

  @Autowired
  private SuburbService suburbService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> getMix() {
    Map<String, Object> map = new HashMap<>();

    map.put("systemprops", systemPropertyService.getAll());
    map.put("suburbs", suburbService.getAll());

    return map;
  }
}
