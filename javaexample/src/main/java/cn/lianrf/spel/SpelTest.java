package cn.lianrf.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianrongfa on 2020/1/11.
 */
public class SpelTest {

    public static void main(String[] args) {
        Role role = getEntity();
        SpelExpressionParser parser = new SpelExpressionParser();

        EvaluationContext ctx = new StandardEvaluationContext(role);

        Expression expression = parser.parseExpression("#role.id");
        Object value = expression.getValue(ctx, role);
        System.out.println(value);
    }

    public static Role getEntity(){
        Role role = new Role();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(11,"xixi"));
        list.add(new User(12,"haha"));
        role.setUsers(list);
        role.setArr(list.toArray(new User[]{}));
        role.setId(1);
        return role;
    }

    public static class Role {
        private int id;
        private List<User> users;
        private User[] arr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

        public User[] getArr() {
            return arr;
        }

        public void setArr(User[] arr) {
            this.arr = arr;
        }
    }
    public static class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}




