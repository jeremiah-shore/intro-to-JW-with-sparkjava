package net.jeremiahshore.treehouse.model;

import java.util.List;

/**
 * Created by Jeremiah Shore at 3/5/2018 10:39 AM for course-ideas-rebuild.
 **/
public interface CourseIdeaDAO {
    boolean add(CourseIdea idea);

    List<CourseIdea> findAll();

    CourseIdea findBySlug(String slug);
}
