package net.jeremiahshore.treehouse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremiah Shore at 3/5/2018 10:40 AM for course-ideas-rebuild.
 **/
public class SimpleCourseIdeaDAO implements CourseIdeaDAO{
    private List<CourseIdea> ideas;

    public SimpleCourseIdeaDAO() {
        this.ideas = new ArrayList<>();
    }

    @Override
    public boolean add(CourseIdea idea) {
        return ideas.add(idea);
    }

    @Override
    public List<CourseIdea> findAll() {
        return new ArrayList<>(ideas);
    }
}
