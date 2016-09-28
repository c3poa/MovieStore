package com.movies.controller;

import com.movies.model.Category;
import com.movies.model.CategoryDao;
import com.movies.model.Movie;
import com.movies.model.MovieDao;
import com.movies.model.OrdersDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    OrdersDao ordersDao;
    
    @RequestMapping("/")
    public String index(){
        return "admin/index";
    }
    
    @RequestMapping("/movies")
    public String movies(@RequestParam(defaultValue = "1") Integer page, ModelMap model){
        List<Movie> movies = movieDao.findByPage(page-1);
        model.addAttribute("movies", movies);
        model.addAttribute("totalpages", movieDao.pages());  
        return "admin/movies";
    }
    
    @RequestMapping(value="/updatemovie", method = RequestMethod.POST)
    public String updateMoviePost(
            @RequestParam Integer id,
            @RequestParam String title,
            @RequestParam String price,
            @RequestParam Integer category,
            @RequestParam MultipartFile photo,
            ModelMap model,HttpServletRequest request) throws FileNotFoundException, IOException{
        List<Category> categories = categoryDao.find();
        
        
        
        model.addAttribute("categories", categories);
        Movie movie = movieDao.getById(id);
        movie.setTitle(title);
        
        if(photo!=null&&!photo.isEmpty()){
            String filepath = request.getServletContext().getRealPath("resources/images");
            FileOutputStream fos = new FileOutputStream(filepath+"/"+photo.getOriginalFilename());
            movie.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        
        movie.setCategory(category);
        movie.setPrice(new BigDecimal(price));
        movieDao.update(movie);
        model.addAttribute("movie", movie);
        return "admin/updatemovie";
    }
    
    @RequestMapping(value="/updatemovie", method = RequestMethod.GET)
    public String updateMovie(@RequestParam Integer id, ModelMap model){
        Movie movie = movieDao.getById(id);
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        model.addAttribute("movie", movie);
        return "admin/updatemovie";
    }
    
    @RequestMapping("/updatecategory")
    public String updateCategory(
            @RequestParam Integer id,
            @RequestParam String name, 
            @RequestParam String description,
            ModelMap model){
        
        Category selectedCategory = categoryDao.getById(id);
        selectedCategory.setName(name);
        selectedCategory.setDescription(description);
        categoryDao.update(selectedCategory);
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", selectedCategory);
        return "admin/categories";
    }
    
    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) Integer id, ModelMap model){
        List<Category> categories = categoryDao.find();
        model.addAttribute("categories", categories);
        if(id!=null){
        Category selectedCategory = categoryDao.getById(id);
        model.addAttribute("selectedCategory", selectedCategory);
        }
        return "admin/categories";
    }
}
