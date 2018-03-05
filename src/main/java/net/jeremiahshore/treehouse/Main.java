package net.jeremiahshore.treehouse;

import net.jeremiahshore.treehouse.model.CourseIdea;
import net.jeremiahshore.treehouse.model.CourseIdeaDAO;
import net.jeremiahshore.treehouse.model.SimpleCourseIdeaDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Jeremiah Shore at 3/4/2018 10:13 PM for course-ideas-rebuild.
 **/
public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        CourseIdeaDAO dao = new SimpleCourseIdeaDAO();

        before((request, response) -> {
            String username = request.cookie("username");
            if(username != null) {
                request.attribute("username", username);
            }
        });

        before("/ideas", (request, response) -> {
            //TODO: send user a message about redirection
            if(request.attribute("username") == null) {
                response.redirect("/");
                halt();
            }
        });

        get("/hello", (request, response) -> "Hello World!");

        get("/", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            model.put("username", request.attribute("username"));
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
            CourseIdea courseIdea = new CourseIdea(title, request.attribute("username"));
            dao.add(courseIdea);
            response.redirect("/ideas");
            return null;
        }, new HandlebarsTemplateEngine());

        post("/ideas/:slug/vote", (request, response) -> {
            CourseIdea idea = dao.findBySlug(request.params("slug"));
            idea.addVoter(request.attribute("username"));
            response.redirect("/ideas");
            return null;
        });

        get("/ideas/:slug/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            CourseIdea courseIdea = dao.findBySlug(request.params("slug"));
            model.put("title", courseIdea.getTitle());
            model.put("voters", courseIdea.getVoters());
            return new ModelAndView(model, "details.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
