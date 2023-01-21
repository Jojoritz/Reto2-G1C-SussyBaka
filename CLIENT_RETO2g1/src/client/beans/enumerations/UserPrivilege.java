package client.beans.enumerations;
/**
 * The enumeration for the user privilege
 * @author ioritz
 */
public enum UserPrivilege {
    /**
     * The student privilege, with this privilege the user only is going to be able to acces to the student part of the application
     */
    STUDENT,
    /**
     * The teacher privilege, with this privilege the user only is going to be able to acces to the teacher part of the application
     */
    TEACHER,
    /**
     * The admin privilege, with this privilege the user can acces to the full application
     */
    ADMIN
}
