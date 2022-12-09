package entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Objects;

public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subjectId;
    private String name;
    private String type;
    private String century;
    private String level;

    /**
     * @associates <{model.Course}>
     */
    private Collection courseWithSubject;

    public Subject() {
        super();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Collection getCourseWithSubject() {
        return courseWithSubject;
    }

    public void setCourseWithSubject(Collection courseWithSubject) {
        this.courseWithSubject = courseWithSubject;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.subjectId);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.century);
        hash = 97 * hash + Objects.hashCode(this.level);
        hash = 97 * hash + Objects.hashCode(this.courseWithSubject);
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
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.century, other.century)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.subjectId, other.subjectId)) {
            return false;
        }
        if (!Objects.equals(this.courseWithSubject, other.courseWithSubject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", name=" + name + ", type=" + type + ", century=" + century + ", level=" + level + ", courseWithSubject=" + courseWithSubject + '}';
    }

}
