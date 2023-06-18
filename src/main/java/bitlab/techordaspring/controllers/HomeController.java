package bitlab.techordaspring.controllers;

import bitlab.techordaspring.models.Brand;
import bitlab.techordaspring.models.Item;
import bitlab.techordaspring.services.BrandService;
import bitlab.techordaspring.services.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/") // @WebServlet(value="/")
    public String homePage(Model model) {
        List<Item> items = itemService.getItems();
        model.addAttribute("zattar", items); // req.setAttribute("zattar", items)
        return "home"; //req.getRequestDispatcher("/home.jsp").forward(req,resp)
    }

    @GetMapping("/addItem")
    public String addItemPage(Model model) {
        List<Brand> brands = brandService.getBrands();
        model.addAttribute("brands", brands);
        return "add-item";
    }

    @PostMapping("/addItem")
    public String addItem(Item item) {
        itemService.addItem(item);
        return "redirect:/"; //resp.sendRedirect("/")
    }

    @PostMapping("/editItem")
    public String editItem(Item item) {
        itemService.editItem(item);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String itemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        List<Brand> brands = brandService.getBrands();
        model.addAttribute("brands", brands);
        return "details";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "") String text, Model model){
        List<Item> items = itemService.search(text);
        model.addAttribute("zattar", items);
        return "home";
    }
}
