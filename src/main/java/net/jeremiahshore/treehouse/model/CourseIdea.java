package net.jeremiahshore.treehouse.model;

/**
 * Created by Jeremiah Shore at 3/5/2018 10:36 AM for course-ideas-rebuild.
 **/
public class CourseIdea {
    private String title;
    private String creator;

    public CourseIdea(String title, String creator) {
        this.title = title;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseIdea)) return false;

        CourseIdea that = (CourseIdea) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return creator != null ? creator.equals(that.creator) : that.creator == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}