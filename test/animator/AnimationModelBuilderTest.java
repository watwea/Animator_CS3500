package animator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.MovableEllipseImpl;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.util.AnimationModelBuilder;
import cs3500.animator.util.KeyFrameAdapterMotion;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.textual.SVGAnimationView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A tester class for methods in the {@link AnimationModelBuilder} class.
 */
public class AnimationModelBuilderTest {

  AnimationModelBuilder builder = new AnimationModelBuilder();

  /*
   * Tests for the setBounds method in
   */
  @Test
  public void setBoundsTest() {
    assertEquals(builder.build().getMovables(), new ArrayList<>());
    assertEquals(builder.build().getMinX(), 0, 0.001);
    assertEquals(builder.build().getMinY(), 0, 0.001);
    assertEquals(builder.build().getWidth(), 0, 0.001);
    assertEquals(builder.build().getHeight(), 0, 0.001);

    builder.setBounds(1, 2, 3, 4);

    assertEquals(builder.build().getMovables(), new ArrayList<>());
    assertEquals(builder.build().getMinX(), 1, 0.001);
    assertEquals(builder.build().getMinY(), 2, 0.001);
    assertEquals(builder.build().getWidth(), 3, 0.001);
    assertEquals(builder.build().getHeight(), 4, 0.001);

    builder.setBounds(-1, -2, 3, 4);

    assertEquals(builder.build().getMovables(), new ArrayList<>());
    assertEquals(builder.build().getMinX(), -1, 0.001);
    assertEquals(builder.build().getMinY(), -2, 0.001);
    assertEquals(builder.build().getWidth(), 3, 0.001);
    assertEquals(builder.build().getHeight(), 4, 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setBoundsTestFail1() {
    builder.setBounds(-1, -2, -3, -4);
    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void setBoundsTestFail2() {
    builder.setBounds(-1, -2, 3, -4);
    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void setBoundsTestFail3() {
    builder.setBounds(-1, -2, -3, 4);
    builder.build();
  }

  /*
   * Tests for the declareShape method in
   */
  @Test
  public void declareShapeTest() {
    assertEquals(builder.build().getMovables(), new ArrayList<>());

    builder.declareShape("e1", "ellipse");
    builder.declareShape("r1", "rectangle");
    builder.declareShape("r2", "rectangle");

    Iterator<Movable> movables = builder.build().getMovables().iterator();

    assertTrue(movables.hasNext());
    Movable movable1 = movables.next();
    assertEquals(movable1.write(), ""
            + "shape e1 ellipse\n");
    assertEquals(movable1.getKeyFrames(), new ArrayList<>());

    assertTrue(movables.hasNext());
    Movable movable2 = movables.next();
    assertEquals(movable2.write(), ""
            + "shape r1 rectangle\n");
    assertEquals(movable2.getKeyFrames(), new ArrayList<>());

    assertTrue(movables.hasNext());
    Movable movable3 = movables.next();
    assertEquals(movable3.write(), ""
            + "shape r2 rectangle\n");
    assertEquals(movable3.getKeyFrames(), new ArrayList<>());

    assertFalse(movables.hasNext());
  }

  @Test(expected = IllegalArgumentException.class)
  public void declareShapeTestFail1() {
    builder.declareShape("shape", "rectangle");
    builder.declareShape("shape2", "rectangle");
    builder.declareShape("shape", "ellipse");
    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void declareShapeTestFail2() {
    builder.declareShape("shape", "rectangle");
    builder.declareShape("shape2", "rect");
    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void declareShapeTestFail3() {
    builder.declareShape("shape", "lol");
    builder.build();
  }

  /*
   * Tests for the addMotion method in
   */
  @Test
  public void addMotionTest() {
    assertEquals(builder.build().getMovables(), new ArrayList<>());

    builder.declareShape("e1", "ellipse");

    builder.addMotion("e1",
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0);
    builder.addMotion("e1",
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 5, 5, 5, 5, 5, 5, 5);
    builder.addMotion("e",
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 5, 5, 5, 5, 5, 5, 5);
    builder.addMotion("e1",
            5, 5, 5, 5, 5, 5, 5, 5,
            10, 10, 10, 10, 10, 10, 10, 10);

    Iterator<Movable> movables = builder.build().getMovables().iterator();

    assertTrue(movables.hasNext());
    Movable movable1 = movables.next();
    assertEquals(movable1.write(), ""
            + "shape e1 ellipse\n");

    Iterator<Motion> motions = KeyFrameAdapterMotion.keyFramesToMotions(
            movable1.getKeyFrames()).iterator();

    assertTrue(motions.hasNext());
    Motion motion1 = motions.next();
    Movable movableA = new MovableEllipseImpl("a");
    AnimationModelImpl.TICK = 1;
    motion1.move(movableA);
    assertEquals(movableA.writeSVG(5), "<ellipse id=\"a\" cx=\"1.0\" cy=\"1.0\" "
            + "rx=\"0.5\" ry=\"0.5\" fill=\"rgb(1,1,1)\" visibility=\"visible\" >\n"
            + "</ellipse>\n");

    assertTrue(motions.hasNext());
    Motion motion2 = motions.next();
    Movable movableB = new MovableEllipseImpl("a");
    AnimationModelImpl.TICK = 2;
    motion2.move(movableB);
    assertEquals(movableB.writeSVG(5), "<ellipse id=\"a\" cx=\"0.0\" cy=\"0.0\" "
            + "rx=\"0.0\" ry=\"0.0\" fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "</ellipse>\n");

    assertFalse(motions.hasNext());

  }

  @Test(expected = IllegalArgumentException.class)
  public void addMotionTestFail1() {
    builder.declareShape("e1", "ellipse");

    builder.addMotion("e1",
            -1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0);
    builder.addMotion("e1",
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 5, 5, 5, 5, 5, 5, 5);
    builder.addMotion("e1",
            5, 5, 5, 5, 5, 5, 5, 5,
            10, 10, 10, 10, 10, 10, 10, 10);

    builder.build();
  }


  /*
   * Tests for the addKeyframe method in
   */
  @Test
  public void addKeyFrameTest() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("e1",
            0, 0, 0, 0, 0, 0, 0, 0);
    builder.addKeyframe("e2",
            0, 0, 0, 0, 0, 0, 0, 0);
    builder.addMotion("e1",
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 5, 5, 5, 5, 5, 5, 5);
    builder.addMotion("e",
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 5, 5, 5, 5, 5, 5, 5);
    builder.addMotion("e1",
            5, 5, 5, 5, 5, 5, 5, 5,
            10, 10, 10, 10, 10, 10, 10, 10);


    AnimationModel model = builder.build();
    Appendable output = new StringBuilder();
    AnimationView view = new SVGAnimationView(10, output);
    AnimationController controller = new AnimationControllerImpl(model, view);

    assertEquals(view.render(), ""
            + "<svg width=\"0.0\" height=\"0.0\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<ellipse id=\"e1\" cx=\"0.0\" cy=\"0.0\" rx=\"0.0\" ry=\"0.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeName=\"cx\" attributeType=\"XML\" from=\"0.0\" to=\"5.0\" "
            + "fill=\"freeze\" begin=\"0.0s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"0.0\" to=\"5.0\" "
            + "fill=\"freeze\" begin=\"0.0s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"rx\" attributeType=\"XML\" from=\"0.0\" to=\"2.5\" "
            + "fill=\"freeze\" begin=\"0.0s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"ry\" attributeType=\"XML\" from=\"0.0\" to=\"2.5\" "
            + "fill=\"freeze\" begin=\"0.0s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(0,0,0)\" "
            + "to=\"rgb(5,5,5)\" fill=\"freeze\" begin=\"0.0s\" dur=\"0.5s\" />\n"
            + "\n"
            + "<animate attributeName=\"cx\" attributeType=\"XML\" from=\"5.0\" to=\"10.0\" "
            + "fill=\"freeze\" begin=\"0.5s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"cy\" attributeType=\"XML\" from=\"5.0\" to=\"10.0\" "
            + "fill=\"freeze\" begin=\"0.5s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"rx\" attributeType=\"XML\" from=\"2.5\" to=\"5.0\" "
            + "fill=\"freeze\" begin=\"0.5s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"ry\" attributeType=\"XML\" from=\"2.5\" to=\"5.0\" "
            + "fill=\"freeze\" begin=\"0.5s\" dur=\"0.5s\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"XML\" from=\"rgb(5,5,5)\" "
            + "to=\"rgb(10,10,10)\" fill=\"freeze\" begin=\"0.5s\" dur=\"0.5s\" />\n"
            + "\n"
            + "</ellipse>\n"
            + "</svg>\n");

    Iterator<Movable> movables = builder.build().getMovables().iterator();

    assertTrue(movables.hasNext());
    Movable movable1 = movables.next();
    assertEquals(movable1.write(), ""
            + "shape e1 ellipse\n");

    Iterator<Motion> motions = KeyFrameAdapterMotion.keyFramesToMotions(
            movable1.getKeyFrames()).iterator();

  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail1() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 0, 0, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail2() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", 0, 0, 0, -1, 0, 0, 0, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail3() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", 0, 0, 0, 0, -1, 0, 0, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail4() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, -1, 0, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail5() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 0, -1, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail6() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 0, 0, -1);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail7() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 256, 0, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail8() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 0, 256, 0);

    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addKeyFrameTestFail9() {
    builder.declareShape("e1", "ellipse");

    builder.addKeyframe("", -1, 0, 0, 0, 0, 0, 0, 256);

    builder.build();
  }

}
