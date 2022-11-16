package controller;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/show")
    public ModelAndView showProduct(){
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", productService.products);
        return modelAndView;
    }

    @GetMapping("/edit/id")
    public ModelAndView editPage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("p", productService.findByid(id));
        return modelAndView;
    }

    @PostMapping("/editsp")
    public ModelAndView editProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/redirect:/show");
        productService.edit(product.getId(),product);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/show");
        productService.add(product);
        return modelAndView;
    }

    @GetMapping("/delete/id")
    public ModelAndView deleteProduct(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/show");
        productService.delete(productService.findByid(id));
        return modelAndView;
    }
}
