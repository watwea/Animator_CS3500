package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationModelBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.textual.SVGAnimationView;
import cs3500.animator.view.textual.TextAnimationView;
import cs3500.animator.view.visual.VisualAnimationView;

import cs3500.animator.view.visual.VisualEditorView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class that houses the main function for running an animation.
 */

public class Excellence {

  /**
   *The main function for this animation program.
   *
   * @param args the command arguments that determine what kind of animation to run,
   *      and with what parameters.
   */

  public static void main(String[] args) {
    Readable readable;
    AnimationView view;
    Appendable output;
    int ticksPerSec;
    AnimationModel model;
    AnimationController controller;
    AnimationBuilder<AnimationModel> builder = new AnimationModelBuilder();

    // parse the input strings to initialize the animation
    boolean correctLen = args.length == 4 || args.length == 6 || args.length == 8;
    boolean allUniqueCommands;
    if (!correctLen) {
      throw new IllegalArgumentException("there are an incorrect number of arguments");
    }
    String command1 = args[0];
    String value1 = args[1];
    String command2 = args[2];
    String value2 = args[3];
    allUniqueCommands = !command1.equals(command2);
    String command3 = "";
    String value3 = "";
    String command4 = "";
    String value4 = "";
    if (args.length >= 6) {
      command3 = args[4];
      value3 = args[5];
      allUniqueCommands = allUniqueCommands
              && (!command1.equals(command3)) && (!command2.equals(command3));
      if (args.length >= 8) {
        command4 = args[6];
        value4 = args[7];
        allUniqueCommands = allUniqueCommands && (!command1.equals(command4))
                && (!command2.equals(command4)) && (!command3.equals(command4));
      }
    }

    if (!allUniqueCommands) {
      throw new IllegalArgumentException("there are repeat commands in the supplied arguments");
    }

    // find and parse the input file into the Readable
    if (command1.equals("-in")) {
      readable = Excellence.parseInput(value1);
    } else if (command2.equals("-in")) {
      readable = Excellence.parseInput(value2);
    } else if (command3.equals("-in")) {
      readable = Excellence.parseInput(value3);
    } else if (command4.equals("-in")) {
      readable = Excellence.parseInput(value4);
    } else {
      throw new IllegalArgumentException("no input command was given");
    }

    model = AnimationReader.parseFile(readable, builder);

    // find and set the speed in ticks/sec, and default to a rate of 1 tick/sec
    if (command1.equals("-speed")) {
      ticksPerSec = Excellence.parseSpeed(value1);
    } else if (command2.equals("-speed")) {
      ticksPerSec = Excellence.parseSpeed(value2);
    } else if (command3.equals("-speed")) {
      ticksPerSec = Excellence.parseSpeed(value3);
    } else if (command4.equals("-speed")) {
      ticksPerSec = Excellence.parseSpeed(value4);
    } else {
      ticksPerSec = 1;
    }

    // find and set the Appendable output, default to System.out
    if (command1.equals("-out")) {
      output = Excellence.parseOutput(value1);
    } else if (command2.equals("-out")) {
      output = Excellence.parseOutput(value2);
    } else if (command3.equals("-out")) {
      output = Excellence.parseOutput(value3);
    } else if (command4.equals("-out")) {
      output = Excellence.parseOutput(value4);
    } else {
      output = System.out;
    }

    // find the view type and initialize the AnimationView
    if (command1.equals("-view")) {
      view = Excellence.initializeView(value1, ticksPerSec, output, model);
    } else if (command2.equals("-view")) {
      view = Excellence.initializeView(value2, ticksPerSec, output, model);
    } else if (command3.equals("-view")) {
      view = Excellence.initializeView(value3, ticksPerSec, output, model);
    } else if (command4.equals("-view")) {
      view = Excellence.initializeView(value4, ticksPerSec, output, model);
    } else {
      throw new IllegalArgumentException("the view type was not specified");
    }

    controller = new AnimationControllerImpl(model, view);

    controller.run();
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

  private static Appendable parseOutput(String output) throws IllegalStateException {
    Appendable appendable;
    if (output.equals("out")) {
      appendable = System.out;
    } else if (output.equals("StringBuilder")) {
      appendable = new StringBuilder();
    } else {
      File f = new File(output);
      if (f.exists()) {
        throw new IllegalStateException("the given output file already exists.");
      }
      try {
        appendable = new FileWriter(output, true);
      } catch (IOException e) {
        System.out.print("There was a problem writing the file:");
        throw new IllegalStateException(e.getMessage());
      }
    }
    return appendable;
  }

  private static int parseSpeed(String string) throws IllegalArgumentException {
    int integer;
    try {
      integer = Integer.parseInt(string);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("the given tick rate is invalid");
    }
    return integer;
  }

  private static AnimationView initializeView(
          String viewType, int ticksPerSecond, Appendable output, AnimationModel model)
          throws IllegalArgumentException {
    AnimationView view;
    switch (viewType) {
      case "text":
        view = new TextAnimationView(
                output);
        break;
      case "visual":
        view = new VisualAnimationView(
                ticksPerSecond, (int) model.getWidth(), (int) model.getHeight());
        break;
      case "svg":
        view = new SVGAnimationView(
                ticksPerSecond, output);
        break;
      case "editor":
        view = new VisualEditorView(
                ticksPerSecond, (int) model.getWidth(), (int) model.getHeight());
        break;
      default:
        throw new IllegalArgumentException("Unexpected view type: " + viewType);
    }
    return view;
  }

}

