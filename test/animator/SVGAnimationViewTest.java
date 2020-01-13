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
import cs3500.animator.view.textual.SVGAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * A class for testing the SVG view of an animation. REQUIRES THE TESTER TO EDIT THE FILE PATHS
 */

public class SVGAnimationViewTest {

  private AnimationModel testModel = new AnimationModelImpl(5, 5, 5, 5,
          new ArrayList<>(), 5);
  private AnimationView testViewSVG = new SVGAnimationView(1, System.out);
  private AnimationController testController = new AnimationControllerImpl(testModel, testViewSVG);

  @Test
  public void testConstructor() {
    AnimationView tester = new SVGAnimationView(1, System.out);
    assertEquals(tester.setController(testController), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ticksLessThanOne() {
    AnimationView tester = new SVGAnimationView(0, System.out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void appendableIsNull() {
    AnimationView tester = new SVGAnimationView(1, null);
  }

  @Test
  public void testOutput() {
    StringBuilder out = new StringBuilder();
    AnimationView tester = new SVGAnimationView(1, out);
    AnimationBuilder<AnimationModel> builder = new AnimationModelBuilder();
    Readable readable = parseInput("C:\\Users\\aadit\\Documents\\School\\"
            + "Object-Oriented_Design\\hw07\\resources\\smalldemo.txt");
    AnimationModel model = AnimationReader.parseFile(readable, builder);
    AnimationControllerImpl controllerTest = new AnimationControllerImpl(model, tester);
    controllerTest.run();
    assertEquals(out.toString(), (""
            + "<svg width=\"720.0\" height=\"720.0\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"0.0\" height=\"0.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"200.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"200.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\" from=\"50.0\" to=\"50.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"height\" attributeType=\"XML\" from=\"100.0\" "
            + "to=\"100.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(255,0,0)\" "
            + "to=\"rgb(255,0,0)\" fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"200.0\" to=\"300.0\" "
            + "fill=\"freeze\" begin=\"10.0s\" dur=\"40.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"200.0\" to=\"300.0\" "
            + "fill=\"freeze\" begin=\"10.0s\" dur=\"40.0s\" />\n"
            + "\n"
            + "\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\" from=\"50.0\" to=\"25.0\" "
            + "fill=\"freeze\" begin=\"51.0s\" dur=\"19.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"300.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"70.0s\" dur=\"30.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"300.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"70.0s\" dur=\"30.0s\" />\n"
            + "\n"
            + "</rect>\n"
            + "<ellipse id=\"C\" cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\" >\n"
            + "<animate attributeName=\"cx\" attributeType=\"XML\" from=\"440.0\" to=\"440.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"70.0\" to=\"70.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"rx\" attributeType=\"XML\" from=\"60.0\" to=\"60.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"ry\" attributeType=\"XML\" from=\"30.0\" to=\"30.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(0,0,255)\" fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"70.0\" to=\"250.0\" "
            + "fill=\"freeze\" begin=\"20.0s\" dur=\"30.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"250.0\" to=\"370.0\" "
            + "fill=\"freeze\" begin=\"50.0s\" dur=\"20.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(0,170,85)\" fill=\"freeze\" begin=\"50.0s\" dur=\"20.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,170,85)\" "
            + "to=\"rgb(0,255,0)\" fill=\"freeze\" begin=\"70.0s\" dur=\"10.0s\" />\n"
            + "\n"
            + "\n"
            + "</ellipse>\n"
            + "</svg>\n"));
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
      AnimationView tester = new SVGAnimationView(1, out);
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
                    + "hw07\\resources\\test1.txt");
    if (f.exists()) {
      throw new IllegalStateException("the given output file already exists.");
    }
    try {
      Appendable out = new FileWriter(f, true);
      AnimationView tester = new SVGAnimationView(1, out);
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
                    + "hw07\\resources\\test1.txt",
            StandardCharsets.ISO_8859_1), ""
            + "<svg width=\"720.0\" height=\"720.0\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"0.0\" height=\"0.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"200.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"200.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\" from=\"50.0\" to=\"50.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"height\" attributeType=\"XML\" from=\"100.0\" "
            + "to=\"100.0\" "
            + "fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(255,0,0)\" "
            + "to=\"rgb(255,0,0)\" fill=\"freeze\" begin=\"1.0s\" dur=\"9.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"200.0\" to=\"300.0\" "
            + "fill=\"freeze\" begin=\"10.0s\" dur=\"40.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"200.0\" to=\"300.0\" "
            + "fill=\"freeze\" begin=\"10.0s\" dur=\"40.0s\" />\n"
            + "\n"
            + "\n"
            + "<animate attributeName=\"width\" attributeType=\"XML\" from=\"50.0\" to=\"25.0\" "
            + "fill=\"freeze\" begin=\"51.0s\" dur=\"19.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"x\" attributeType=\"XML\" from=\"300.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"70.0s\" dur=\"30.0s\" />\n"
            + "<animate attributeName=\"y\" attributeType=\"XML\" from=\"300.0\" to=\"200.0\" "
            + "fill=\"freeze\" begin=\"70.0s\" dur=\"30.0s\" />\n"
            + "\n"
            + "</rect>\n"
            + "<ellipse id=\"C\" cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\" >\n"
            + "<animate attributeName=\"cx\" attributeType=\"XML\" from=\"440.0\" to=\"440.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"70.0\" to=\"70.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"rx\" attributeType=\"XML\" from=\"60.0\" to=\"60.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"ry\" attributeType=\"XML\" from=\"30.0\" to=\"30.0\" "
            + "fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(0,0,255)\" fill=\"freeze\" begin=\"6.0s\" dur=\"14.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"70.0\" to=\"250.0\" "
            + "fill=\"freeze\" begin=\"20.0s\" dur=\"30.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"250.0\" to=\"370.0\" "
            + "fill=\"freeze\" begin=\"50.0s\" dur=\"20.0s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(0,170,85)\" fill=\"freeze\" begin=\"50.0s\" dur=\"20.0s\" />\n"
            + "\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,170,85)\" "
            + "to=\"rgb(0,255,0)\" fill=\"freeze\" begin=\"70.0s\" dur=\"10.0s\" />\n"
            + "\n"
            + "\n"
            + "</ellipse>\n"
            + "</svg>\n");
    f.delete();

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

  private static String readFile(String path, Charset encoding) throws IOException {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
    } catch (java.io.IOException e) {
      throw new IllegalStateException("Bad File");
    }
  }

}
