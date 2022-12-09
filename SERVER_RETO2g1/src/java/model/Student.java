package model;

import java.util.Collection;
import java.util.Objects;

public class Student extends User{
    private static final long serialVersionUID = 1L;

    /**
     * @associates <{model.Course}>
     */
    private Collection studyingCourses;

    public Student() {
        super();
    }

    public Collection getStudyingCourses() {
        return studyingCourses;
    }

    public void setStudyingCourses(Collection studyingCourses) {
        this.studyingCourses = studyingCourses;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.studyingCourses);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.studyingCourses, other.studyingCourses)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "studyingCourses=" + studyingCourses + '}';
    }
    
    
}
