package sbk.mvc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("dog")
@RestController
public class DogRestController
{
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Dog getDog() {
    return new Dog("Collie", "blackish");
  }


  public static class Dog {
    private String breed;
    private String colour;

    public Dog(String breed, String colour)
    {
      this.breed = breed;
      this.colour = colour;
    }

    public String getBreed()
    {
      return breed;
    }

    public String getColour()
    {
      return colour;
    }
  }
}
