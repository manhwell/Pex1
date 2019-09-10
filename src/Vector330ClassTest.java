import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Vector330ClassTest {

    private double x1 = 4.3;
    private double y1 = 5.6;
    private double x2 = 3.0;
    private double y2 = 4.5;
    private String parseTest = "| < " + x1 + ", " + x2 + " >";
    private String parseTestBrkn = "| < " + x1 + ", " + x2 + " >";
    private Vector330Class vector1;
    private Vector330Class vector2;
    private Vector330Class vector3;
    private Vector330Class testVector;
    private double resultValue;
    private double EPS = .0000000001;

    @BeforeEach
    void setUp() {
        vector1 = new Vector330Class(x1, y1);
        vector2 = new Vector330Class(x2, y2);
        vector3 = new Vector330Class(x1, y1);
    }

    @AfterEach
    void tearDown() {
        vector1 = null;
        vector2 = null;
        vector3 = null;
        testVector = null;
        resultValue = 0;
    }

    @Test
    void getX() {
        assert(vector1.getX() == x1);
        assertEquals(vector1.getX(), x1);
    }

    @Test
    void getXint() {
        assert(vector1.getXint() == (int) x1);
        assertEquals(vector1.getXint(), (int) x1);
    }

    @Test
    void getXlong() {
        assert(vector1.getXlong() == (long) x1);
        assertEquals(vector1.getXlong(), (long) x1);
    }

    @Test
    void getY() {
        assert(vector1.getY() == y1);
        assertEquals(vector1.getY(), y1);
    }

    @Test
    void getYint() {
        assert(vector1.getYint() == (int) y1);
        assertEquals(vector1.getYint(), (int) y1);
    }

    @Test
    void getYlong() {
        assert(vector1.getYlong() == (long) y1);
        assertEquals(vector1.getYlong(), (long) y1);
    }

    @Test
    void setX() {
        vector1.setX(3.28);
        assert(vector1.getX() == 3.28);
        assertEquals(vector1.getX(), 3.28);
    }

    @Test
    void SetXInt() {
        vector1.setX(3.28);
        assert(vector1.getXint() == 3);
        assertEquals(vector1.getXint(), 3);
    }

    @Test
    void SetXLong() {
        vector1.setX((long) 3.28);
        assert(vector1.getX() == (long) 3.28);
        assertEquals(vector1.getX(), (long) 3.28);
    }

    @Test
    void setY() {
        vector1.setY(6.3);
        assert(vector1.getY() == 6.3);
        assertEquals(vector1.getY(), 6.3);
    }

    @Test
    void SetYInt() {
        vector1.setY(6);
        assert(vector1.getY() == 6);
        assertEquals(vector1.getY(), 6);
    }

    @Test
    void SetYLong() {
        vector1.setY((long) 6.3);
        assert(vector1.getY() == (long) 6.3);
        assertEquals(vector1.getY(), (long) 6.3);
    }

    @Test
    void testEquals() {
        assert(vector1.equals(vector3));
        assertTrue(vector1.equals(vector3));
    }

    @Test
    void add() {
        testVector = vector1.add(vector2);
        assert(testVector.getX() - (x1 + x2) <= EPS && testVector.getY() - (y1 + y2) <= EPS);
    }

    @Test
    void subtract() {
        testVector = vector1.subtract(vector2);
        assert(testVector.getX() - (x1 - x2) <= EPS && testVector.getY() - (y1 - y2) <= EPS);
    }

    @Test
    void dotProduct() {
        resultValue = vector1.dotProduct(vector2);
        assert(resultValue - ((x1 * x2) + (y1 * y2)) <= EPS);
    }

    @Test
    void scale() {
        testVector = vector1.scale(3.0);
        assert(testVector.getX() - (x1 * 3.0) <= EPS);
        assert(testVector.getX() - (y1 * 3.0) <= EPS);
    }

    @Test
    void magnitude() {
        resultValue = vector1.magnitude();
        assert(resultValue - Math.sqrt((x1 * x1) + (y1 * y1)) <= EPS);
    }

    @Test
    void direction() {
        resultValue = vector1.direction();
        assert(resultValue - Math.atan2(y1, x1) <= EPS);
    }

    @Test
    void normalize() {
        testVector = vector1.normalize();
        assert(testVector.getX() - x1 * (1/testVector.magnitude()) <= EPS);
        assert(testVector.getY() - y1 * (1/testVector.magnitude()) <= EPS);
    }

    @Test
    void testToString() {
        assert(vector1.toString().equals("< " + x1 + ", " + y1 + " >"));
    }

    @Test
    void parseVector() throws Exception {
        Scanner userCmd = new Scanner(parseTest);
        Scanner userCmdBrkn = new Scanner(parseTestBrkn);
        testVector = Vector330Class.parseVector(userCmd);
        assert(testVector.getX() == x1);
        //TODO figure out how to assert that this throws an exception
        assertThrows(Exception.class, (Executable) Vector330Class.parseVector(userCmdBrkn));
        userCmd.close();
        userCmdBrkn.close();
    }
}