package net.jeremiahshore.treehouse;

import net.jeremiahshore.treehouse.model.CourseIdea;
import net.jeremiahshore.treehouse.model.CourseIdeaDAO;
import net.jeremiahshore.treehouse.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static  spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by Jeremiah Shore at 3/4/2018 10:13 PM for course-ideas-rebuild.
 **/
public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        CourseIdeaDAO dao = new SimpleCourseIdeaDAO();

        get("/hello", (request, response) -> "Hello World!");

        get("/", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            model.put("username", request.cookie("username"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sign-in", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            String username = request.queryParams("username");
            response.cookie("username", username);
            model.put("username", username);
            response.redirect("/");
            return null;
        });

        get("/ideas", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("ideas", dao.findAll());
            return new ModelAndView(model, "ideas.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ideas", (request, response) -> {
            String title = request.queryParams("title");
            //TODO: this username is tied to the cookie implementation
            CourseIdea courseIdea = new CourseIdea(title, request.cookie("username"));
            dao.add(courseIdea);
            response.redirect("/ideas");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
