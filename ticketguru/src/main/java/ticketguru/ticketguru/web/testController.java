package ticketguru.ticketguru.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class testController {

    @RequestMapping("kehitysymparisto")
    @ResponseBody
    public String showTest(){
        return "Kehitysympäristösi toimii!";
    }

}
