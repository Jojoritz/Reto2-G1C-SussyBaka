package model;

import java.util.Collection;
import java.util.Objects;

public class Teacher extends User {
    private static final long serialVersionUID = 1L;

    /**
     * @associates <{model.Course}>
     */
    private Collection teachingCourses;


    public Teacher() {
        super();
    }

    public Collection getTeachingCourses() {
        return teachingCourses;
    }

    public void setTeachingCourses(Collection teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.teachingCourses);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teacher other = (Teacher) obj;
        if (!Objects.equals(this.teachingCourses, other.teachingCourses)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teachingCourses=" + teachingCourses + '}';
    }
    
    
}
