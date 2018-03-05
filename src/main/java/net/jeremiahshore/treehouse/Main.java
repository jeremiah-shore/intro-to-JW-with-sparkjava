package net.jeremiahshore.treehouse;

import net.jeremiahshore.treehouse.model.CourseIdeaDAO;
import net.jeremiahshore.treehouse.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
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
            return new ModelAndView(model, "sign-in.hbs");
            }, new HandlebarsTemplateEngine());
    }
}
