package Users;

import org.junit.Assert;
import org.junit.Test;

public class UnRegisteredUserTest {
    static User user = new UnRegisteredUser("Xiaobing");

    @Test
    public void getInformationTest() {
        Assert.assertEquals("You have not registered.", user.getInformation());
    }

    @Test
    public void getTypeTest() {
        Assert.assertEquals("UnRegisteredUser", user.getType());
    }

    @Test
    public void setIntroduceTest() {
        user.setIntroduce("I am student in MET.");
        Assert.assertEquals("You have not registered.", user.introduce);
    }
}
