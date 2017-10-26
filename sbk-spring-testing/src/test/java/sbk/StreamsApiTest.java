package sbk;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Created by simonkahl on 7/10/17.
 */
public class StreamsApiTest
{
  @Test
  public void getFirstValueForEachParameter() {
    Map<String, String[]> map;
    map = new HashMap<>();
    map.put("a", new String[] {"1", "2", "3"});
    map.put("b", new String[] {"4", "5"});
    map.put("c", new String[] {"6"});

    Map<String, Object> result = getFirstValueForEachParameter(map);
    System.out.println(result);
  }

  private Map<String, Object> getFirstValueForEachParameter(Map<String, String[]> parameterMap) {
    return parameterMap.entrySet().stream().collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()[0]));
  }
}
