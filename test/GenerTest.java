import generation.Gener;
import static generation.CellProperties.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;

public class GenerTest<e> {

    @Test
    public void initTest() {
        File in = new File("test/in.txt");
        try{
            Gener g = new Gener(in);
            assertNotNull(g);
            for(int i=0; i<5; i++) {
                g.printOnConsole();
                g.nextStep();
                if(i==2) {
                    assertEquals(TAIL,g.getValue(1,3));
                }
            }
        } catch (Exception e){
            System.err.println("File impossible to read");
            System.exit(2);
        }



    }

    @Test
    public void initStructTest() {
        File in = new File("test/in_struct.txt");
        System.out.println(in.canExecute());
        try{
            Gener g = new Gener(in);
            assertNotNull(g);
            for(int i=0; i<5; i++) {
                g.printOnConsole();
                g.nextStep();
                if(i==2) {
                    assertEquals(TAIL,g.getValue(1,3));
                }
            }
        } catch(Exception e){
            System.err.println("File impossible to read");
            System.exit(2);
        }
    }

}