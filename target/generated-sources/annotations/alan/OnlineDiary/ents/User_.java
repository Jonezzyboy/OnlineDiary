package alan.OnlineDiary.ents;

import alan.OnlineDiary.ents.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-28T15:05:33")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Appointment> appointments;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, Long> user_id;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> postcode;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}