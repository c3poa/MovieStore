package com.movies.controller;

import com.movies.model.Category;
import com.movies.model.CategoryDao;
import com.movies.model.Movie;
import com.movies.model.MovieDao;
import com.movies.model.Orders;
import com.movies.model.OrdersDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    OrdersDao ordersDao;
    
    @RequestMapping("/confirm")
    public String confirmOrder(@RequestParam(required = false) String userdata, HttpServletRequest request, ModelMap model){
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        if(userdata==null){
            
        } else {
            StringBuilder sb = new StringBuilder();
            HttpSession session = request.getSession();
            HashMap<Integer,Movie> sessionMovies = (HashMap<Integer,Movie>)session.getAttribute("cart");
            sb.append("[");
            for(Map.Entry<Integer,Movie> m : sessionMovies.entrySet()){
                sb.append("{\"id\":");
                sb.append(m.getValue().getId());
                sb.append(",\"q\":");
                sb.append(m.getValue().getQuantity());
                sb.append("},");
            }
            String substr = sb.substring(0, sb.length()-1);
            substr += "]";
            Orders order = new Orders();
            order.setOrdertime(new Date(new java.util.Date().getTime()));
            order.setProducts(substr);
            order.setUserdata(userdata);
            ordersDao.save(order);
            
            session.removeAttribute("cart");
            return "confirmsuccess";
        }
        
        return "confirm";
    }
    
    @RequestMapping("/remove")
    public String remove(@RequestParam(required = true) int id, HttpServletRequest request, ModelMap model){
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        HttpSession session = request.getSession();
        if(session.getAttribute("cart")!=null){
            HashMap<Integer,Movie> movies = (HashMap<Integer,Movie>)session.getAttribute("cart");
            if(movies.containsKey(id)){
                movies.remove(id);
            }
        }
    
        return "remove";
    }
    
    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, ModelMap model){
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        List<Movie> movies = new ArrayList<Movie>();
        
        HttpSession session = request.getSession();
        
        if(session.getAttribute("cart")!=null){
        HashMap<Integer,Movie> sessionMovies = (HashMap<Integer,Movie>)session.getAttribute("cart");
            for(Map.Entry<Integer,Movie> m : sessionMovies.entrySet()){
                movies.add(m.getValue());
            }
        }
        model.addAttribute("movies", movies);
        
        return "cart";
    }
    
    @RequestMapping("/addtobasket")
    public String addToBasket(ModelMap model, HttpServletRequest request, @RequestParam(required = true) Integer id, @RequestParam(required = true) Integer quantity){
        HttpSession session = request.getSession();
        HashMap<Integer,Movie> cart;
        if(session.getAttribute("cart")==null){
            session.setAttribute("cart",new HashMap<Integer,Movie>());
        }
        cart = (HashMap<Integer,Movie>)session.getAttribute("cart");
        
        if(!cart.containsKey(id)){
            Movie movie = movieDao.getById(id);
            movie.quantity = quantity;
            cart.put(id, movie);
        } else {
            Movie movieFromCart = cart.get(id);
            movieFromCart.quantity += quantity;
        }
        
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        
        return "addedtobasket";
    }
    
    @RequestMapping("/tobasket/{id}")
    public String toBasket(@PathVariable int id, ModelMap model){
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        Movie movie = movieDao.getById(id);
        model.addAttribute("movie", movie);
        return "tobasket";
    }
    
    @RequestMapping("/")
    public String index(ModelMap model){
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        return byCategory(1, model);
    }
    
    @RequestMapping("/{id}")
    public String byCategory(@PathVariable int id, ModelMap model){
        List<Category> categories = categoryDao.find();
        List<Movie> movies = movieDao.findByCategory(id);
        model.addAttribute("categories", categories);
        model.addAttribute("movies", movies);
        return "index";
    }
}
