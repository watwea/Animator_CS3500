package animator;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationModelBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.textual.TextAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * A class for testing the Text view of an animation. REQUIRES THE TESTER TO EDIT THE FILE PATHS
 */

public class TextAnimationViewTest {

  AnimationModel testModel = new AnimationModelImpl(5, 5, 5, 5, new ArrayList<>(), 5);
  AnimationView testViewText = new TextAnimationView(System.out);
  AnimationController testController = new AnimationControllerImpl(testModel, testViewText);

  @Test
  public void testConstructor() {
    AnimationView tester = new TextAnimationView(System.out);
    assertEquals(tester.setController(testController), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void appendableIsNull() {
    AnimationView tester = new TextAnimationView(null);
  }

  @Test
  public void testOutput() {
    StringBuilder out = new StringBuilder();
    AnimationView tester = new TextAnimationView(out);
    AnimationBuilder<AnimationModel> builder = new AnimationModelBuilder();
    Readable readable = parseInput(
            "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                    + "hw07\\resources\\smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(readable, builder);
    AnimationControllerImpl controllerTest = new AnimationControllerImpl(model, tester);
    controllerTest.run();
    assertEquals(out.toString(), (""
            + "canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "shape C ellipse\n"
            + "motion R 1 200.0 200.0 50.0 100.0 255 0 0  10 200.0 200.0 50.0 100.0 255 0 0\n"
            + "motion R 10 200.0 200.0 50.0 100.0 255 0 0  50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 50 300.0 300.0 50.0 100.0 255 0 0  51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 51 300.0 300.0 50.0 100.0 255 0 0  70 300.0 300.0 25.0 100.0 255 0 0\n"
            + "motion R 70 300.0 300.0 25.0 100.0 255 0 0  100 200.0 200.0 25.0 100.0 255 0 0\n"
            + "motion C 6 440.0 70.0 120.0 60.0 0 0 255  20 440.0 70.0 120.0 60.0 0 0 255\n"
            + "motion C 20 440.0 70.0 120.0 60.0 0 0 255  50 440.0 250.0 120.0 60.0 0 0 255\n"
            + "motion C 50 440.0 250.0 120.0 60.0 0 0 255  70 440.0 370.0 120.0 60.0 0 170 85\n"
            + "motion C 70 440.0 370.0 120.0 60.0 0 170 85  80 440.0 370.0 120.0 60.0 0 255 0\n"
            + "motion C 80 440.0 370.0 120.0 60.0 0 255 0  100 440.0 370.0 120.0 60.0 0 255 0"));
  }


  @Test(expected = IllegalStateException.class)
  public void testOutputToFileExists() throws IOException {
    File f = new File(
            "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                    + "hw07\\resources\\test.txt");
    if (f.exists()) {
      throw new IllegalStateException("the given output file already exists.");
    }
    try {
      Appendable out = new FileWriter(f, true);
      AnimationView tester = new TextAnimationView(out);
      AnimationBuilder<AnimationModel> builder = new AnimationModelBuilder();
      Readable readable = parseInput(
              "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                      + "hw07\\resources\\smalldemo.txt");
      AnimationModel model = AnimationReader.parseFile(readable, builder);
      AnimationControllerImpl controllerTest = new AnimationControllerImpl(model, tester);
      controllerTest.run();
    } catch (IOException e) {
      System.out.print("There was a problem writing the file:");
      throw new IllegalStateException(e.getMessage());
    }
    assertEquals(readFile(
            "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                    + "hw07\\resources\\test.txt",
            StandardCharsets.ISO_8859_1), "");

  }


  @Test
  public void testOutputToFile() throws IOException {
    File f = new File(
            "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                    + "hw07\\resources\\testX.txt");
    if (f.exists()) {
      throw new IllegalStateException("the given output file already exists.");
    }
    try {
      Appendable out = new FileWriter(f, true);
      AnimationView tester = new TextAnimationView(out);
      AnimationBuilder<AnimationModel> builder = new AnimationModelBuilder();
      Readable readable = parseInput(
              "C:\\Users\\aadit\\Documents\\School\\Object-Oriented_Design\\"
                      + "hw07\\resources\\testX.txt");
      AnimationModel model = AnimationReader.parseFile(readable, builder);
      AnimationControllerImpl controllerTest = new AnimationControllerImpl(model, tester);
      controllerTest.run();
    } catch (IOException e) {
      System.out.print("There was a problem writing the file:");
      throw new IllegalStateException(e.getMessage());
    }
    assertEquals(readFile("/Users/john/IdeaProjects/ExCELlence/resources/testX.txt",
            StandardCharsets.ISO_8859_1), "canvas 200 70 360 360\n"
            + "shape R rectangle\n"
            + "shape C ellipse\n"
            + "motion R 1 200.0 200.0 50.0 100.0 255 0 0  10 200.0 200.0 50.0 100.0 255 0 0\n"
            + "motion R 10 200.0 200.0 50.0 100.0 255 0 0  50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 50 300.0 300.0 50.0 100.0 255 0 0  51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 51 300.0 300.0 50.0 100.0 255 0 0  70 300.0 300.0 25.0 100.0 255 0 0\n"
            + "motion R 70 300.0 300.0 25.0 100.0 255 0 0  100 200.0 200.0 25.0 100.0 255 0 0\n"
            + "motion C 6 440.0 70.0 120.0 60.0 0 0 255  20 440.0 70.0 120.0 60.0 0 0 255\n"
            + "motion C 20 440.0 70.0 120.0 60.0 0 0 255  50 440.0 250.0 120.0 60.0 0 0 255\n"
            + "motion C 50 440.0 250.0 120.0 60.0 0 0 255  70 440.0 370.0 120.0 60.0 0 170 85\n"
            + "motion C 70 440.0 370.0 120.0 60.0 0 170 85  80 440.0 370.0 120.0 60.0 0 255 0\n"
            + "motion C 80 440.0 370.0 120.0 60.0 0 255 0  100 440.0 370.0 120.0 60.0 0 255 0");
    f.delete();
  }

  private static String readFile(String path, Charset encoding) throws IOException {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
    } catch (java.io.IOException e) {
      throw new IllegalStateException("Bad File");
    }
  }

  private static Readable parseInput(String animationFile) throws IllegalStateException {
    Readable readable;
    try {
      readable = new FileReader(animationFile);
    } catch (FileNotFoundException e) {
      System.out.print("There was a problem reading the file:");
      throw new IllegalStateException(e.getMessage());
    }
    return readable;
  }

}
