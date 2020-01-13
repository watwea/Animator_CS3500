package cs3500.animator.view.visual;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.AnimationView;

/**
 * Represents a view that can start, pause, resume, and restart an animationPanel and edit its
 * shapes and motions.
 */
public class VisualEditorView extends JFrame implements AnimationView, ActionListener {
  private final int ticksPerSecond;
  private AnimationController controller;
  private DrawingPanel animationPanel;

  private int animationWidth;
  private int animationHeight;

  private JPanel menuPanel;
  private JTextField speedTextField;

  private JDialog addShapeDialog;
  private String addShapeType = "rectangle";

  private String selectedShapeName = "shape name";
  private String selectedKeyFrameStr = "T: 0 X: 0 Y: 0 W: 0 H: 0 R: 0 G: 0 B: 0";
  private int selectedKeyFrameIdx = 0;

  private JDialog removeShapeDialog;

  private JDialog selectShapeDialog;
  private JDialog addKeyFrameDialog;

  /**
   * Constructs a new editor view with an animation of the given width and height, and an editing
   * frame on the bottom. It runs the animation starting at the given tick, but can change.
   *
   * @param ticksPerSecond  The ticks per second of the animation
   * @param animationWidth  the width of the animation
   * @param animationHeight the height of the animation
   */
  public VisualEditorView(int ticksPerSecond, int animationWidth, int animationHeight) {
    super("Visual Editor View");
    this.getContentPane().setLayout(new BorderLayout());
    this.ticksPerSecond = ticksPerSecond;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.animationWidth = animationWidth;
    this.animationHeight = animationHeight;
    int width = animationWidth * 2;
    int height = animationHeight * 2 + 100;

    this.makeAnimationPanel(animationWidth, animationHeight);
    JScrollPane animationScrollPane = new JScrollPane(this.animationPanel);
    this.getContentPane().add(animationScrollPane, BorderLayout.CENTER);
    this.animationPanel.setVisible(true);

    this.makeMenuPanel(animationWidth, 100);
    JScrollPane menuScrollPane = new JScrollPane(this.menuPanel);
    this.getContentPane().add(menuScrollPane, BorderLayout.SOUTH);
    this.menuPanel.setVisible(true);

    this.setMinimumSize(new Dimension(500, 500));
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.WHITE);
    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.getContentPane().setVisible(true);
    this.setVisible(true);
  }


  @Override
  public String render() {
    return null;
  }

  @Override
  public void output() {
    controller.moveAll();
    animationPanel.draw(controller.getMovables());
    this.makeAnimationPanel(animationWidth, animationHeight);
    this.repaint();
  }

  @Override
  public int setController(AnimationController controller) {
    this.controller = controller;
    return this.ticksPerSecond;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    e.getActionCommand();
  }

  private void makeAnimationPanel(int width, int height) {
    animationPanel = new DrawingPanel();
    animationPanel.setMinimumSize(new Dimension(width, height));
    animationPanel.setPreferredSize(new Dimension(width * 2, height * 2));
    animationPanel.setBackground(Color.WHITE);
  }

  private void makeMenuPanel(int width, int height) {
    menuPanel = new JPanel();
    menuPanel.setLayout(new GridLayout(2, 1));
    JPanel videoPanel = new JPanel();
    videoPanel.setLayout(new FlowLayout());
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new FlowLayout());
    menuPanel.add(videoPanel);
    menuPanel.add(editorPanel);

    JButton playButton = new JButton("Play");
    playButton.addActionListener(e -> controller.play());
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener(e -> controller.pause());
    JCheckBox reverseCheckBox = new JCheckBox("Reverse");
    reverseCheckBox.addActionListener(e -> this.controller.toggleRewind());
    JCheckBox loopCheckBox = new JCheckBox("Loop");
    loopCheckBox.addActionListener(e -> this.controller.toggleLoop());
    loopCheckBox.setSelected(true);
    speedTextField = new JTextField(5);
    JButton speedButton = new JButton("Set FPS");
    speedButton.addActionListener(e -> {
      this.controller.setTicksPerSecond(tickrateFromText(this.speedTextField.getText()));
    });

    videoPanel.add(playButton);
    videoPanel.add(pauseButton);
    videoPanel.add(reverseCheckBox);
    videoPanel.add(loopCheckBox);
    videoPanel.add(speedTextField);
    videoPanel.add(speedButton);

    JButton addShapeButton = new JButton("Add Shape");
    addShapeButton.addActionListener(e -> this.addShapeBox());
    JButton removeShapeButton = new JButton("Remove Shape");
    removeShapeButton.addActionListener(e -> this.deleteShapeBox());
    JButton addKeyFrameButton = new JButton("Add Key Frame");
    addKeyFrameButton.addActionListener(e -> this.addKeyFrameBox());
    JButton editKeyFrameButton = new JButton("Edit Key Frame");
    editKeyFrameButton.addActionListener(e -> this.editKeyFrameBox());
    JButton removeKeyFrameButton = new JButton("Remove Key Frame");
    removeKeyFrameButton.addActionListener(e -> this.removeKeyFrameBox());

    editorPanel.add(addShapeButton);
    editorPanel.add(removeShapeButton);
    editorPanel.add(addKeyFrameButton);
    editorPanel.add(editKeyFrameButton);
    editorPanel.add(removeKeyFrameButton);

    menuPanel.setMinimumSize(new Dimension(width, height));
    menuPanel.setPreferredSize(new Dimension(width * 2, height));
    menuPanel.setBackground(Color.WHITE);
    menuPanel.setVisible(true);
  }

  private int tickrateFromText(String string) {
    try {
      int rate = Integer.parseInt(string);
      this.resultBox(
              "Tick-rate changed to " + rate + " tick per second.");
      return 1000 / rate;
    } catch (NumberFormatException e) {
      int rate = 1;
      this.resultBox(
              "Invalid input. Tick-rate changed to " + rate + " tick per second.");
      return 1000 / rate;
    }
  }

  private void addShapeBox() {
    // Create the dialog box
    addShapeDialog = new JDialog(this, "Add Shape",
            Dialog.ModalityType.APPLICATION_MODAL);

    // create a main panel for this to rest on and add it to the content pane
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(3, 1, 0, 5));
    addShapeDialog.getContentPane().add(mainPanel);

    // create the sub-panels of the main panel, set their layouts, and add them to the main panel
    JPanel nameTypePanel = new JPanel();
    nameTypePanel.setLayout(new FlowLayout());
    JPanel positionSizePanel = new JPanel();
    positionSizePanel.setLayout(new GridLayout(1, 2, 5, 0));
    JPanel colorAndSubmitPanel = new JPanel();
    colorAndSubmitPanel.setLayout(new BorderLayout());
    mainPanel.add(nameTypePanel);
    mainPanel.add(positionSizePanel);
    mainPanel.add(colorAndSubmitPanel);

    // create the name and type components, group them, and add them to the nameTypePanel
    JLabel shapeNameLabel = new JLabel("New Shape Name: ");
    JTextField shapeName = new JTextField(10);
    JLabel shapeTypeLabel = new JLabel("Shape Type: ");
    JRadioButton rectangleRadio = new JRadioButton("Rectange");
    rectangleRadio.addActionListener(e -> addShapeType = "rectangle");
    JRadioButton ellipseRadio = new JRadioButton("Ellipse");
    ButtonGroup radioGroupShapes = new ButtonGroup();
    radioGroupShapes.add(rectangleRadio);
    radioGroupShapes.add(ellipseRadio);
    ellipseRadio.addActionListener(e -> addShapeType = "ellipse");
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2, 1));
    buttonPanel.add(rectangleRadio);
    buttonPanel.add(ellipseRadio);
    nameTypePanel.add(shapeNameLabel);
    nameTypePanel.add(shapeName);
    nameTypePanel.add(shapeTypeLabel);
    nameTypePanel.add(buttonPanel);

    // create the position components and add them to the positionPanel
    JLabel shapeLabelX = new JLabel("X: ");
    JLabel shapeLabelY = new JLabel("Y: ");
    JTextField shapeX = new JTextField(10);
    JTextField shapeY = new JTextField(10);
    JPanel positionPanel = new JPanel();
    positionPanel.setLayout(new GridLayout(2, 1, 0, 5));
    JPanel xPanel = new JPanel();
    xPanel.setLayout(new FlowLayout());
    xPanel.add(shapeLabelX);
    xPanel.add(shapeX);
    positionPanel.add(xPanel);
    JPanel yPanel = new JPanel();
    yPanel.setLayout(new FlowLayout());
    yPanel.add(shapeLabelY);
    yPanel.add(shapeY);
    positionPanel.add(yPanel);

    // create the size components and add them to the sizePanel
    JLabel shapeLabelWidth = new JLabel("Width: ");
    JLabel shapeLabelHeight = new JLabel("Height: ");
    JTextField shapeWidth = new JTextField(8);
    JTextField shapeHeight = new JTextField(8);
    JPanel sizePanel = new JPanel();
    sizePanel.setLayout(new GridLayout(2, 2, 0, 5));
    JPanel widthPanel = new JPanel();
    widthPanel.setLayout(new FlowLayout());
    widthPanel.add(shapeLabelWidth);
    widthPanel.add(shapeWidth);
    sizePanel.add(widthPanel);
    JPanel heightPanel = new JPanel();
    heightPanel.setLayout(new FlowLayout());
    heightPanel.add(shapeLabelHeight);
    heightPanel.add(shapeHeight);
    sizePanel.add(heightPanel);

    // add the sub panels to the positionSizePanel
    positionSizePanel.add(positionPanel);
    positionSizePanel.add(sizePanel);

    // create the color and submit components and add them to the colorAndSubmitPanel
    JLabel shapeLabelRed = new JLabel("Red [0, 255]: ");
    JLabel shapeLabelGreen = new JLabel("Green [0, 255]: ");
    JLabel shapeLabelBlue = new JLabel("Blue [0, 255]: ");
    JTextField shapeRed = new JTextField(3);
    JTextField shapeGreen = new JTextField(3);
    JTextField shapeBlue = new JTextField(3);
    JButton submitAddShape = new JButton("Add Shape");
    submitAddShape.addActionListener(e -> {
      try {
        this.controller.addShape(shapeName.getText(), addShapeType);
        this.controller.addKeyFrame(shapeName.getText(), 0,
                textAsDouble(shapeX.getText()), textAsDouble(shapeY.getText()),
                textAsDouble(shapeWidth.getText()), textAsDouble(shapeHeight.getText()),
                (int) textAsDouble(shapeRed.getText()), (int) textAsDouble(shapeGreen.getText()),
                (int) textAsDouble(shapeBlue.getText()));
        this.controller.addKeyFrame(shapeName.getText(), 100,
                textAsDouble(shapeX.getText()), textAsDouble(shapeY.getText()),
                textAsDouble(shapeWidth.getText()), textAsDouble(shapeHeight.getText()),
                (int) textAsDouble(shapeRed.getText()), (int) textAsDouble(shapeGreen.getText()),
                (int) textAsDouble(shapeBlue.getText()));
        this.output();
        addShapeDialog.dispose();
        this.resultBox("Shape successfully added! PLease close the dialogue boxes.");
      } catch (IllegalArgumentException | IllegalStateException addShapeException) {
        this.resultBox(
                "Adding the shape failed. Here is the message: "
                        + addShapeException.getMessage());
      }
    });
    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(new FlowLayout());
    colorPanel.add(shapeLabelRed);
    colorPanel.add(shapeRed);
    colorPanel.add(shapeLabelGreen);
    colorPanel.add(shapeGreen);
    colorPanel.add(shapeLabelBlue);
    colorPanel.add(shapeBlue);
    colorAndSubmitPanel.add(colorPanel, BorderLayout.CENTER);
    colorAndSubmitPanel.add(submitAddShape, BorderLayout.EAST);

    addShapeDialog.setSize(new Dimension(400, 200));
    addShapeDialog.setBackground(Color.BLACK);
    addShapeDialog.setLocationRelativeTo(null);
    addShapeDialog.setVisible(true);
  }

  private void deleteShapeBox() {
    removeShapeDialog = new JDialog(this, "Action Result",
            Dialog.ModalityType.APPLICATION_MODAL);
    removeShapeDialog.setLayout(new FlowLayout());
    removeShapeDialog.setSize(new Dimension(400, 100));
    removeShapeDialog.setLocationRelativeTo(null);

    JLabel deleteShapeLabel = new JLabel("Select Shape to Remove: ");
    JComboBox<String> shapeNamesBox = new JComboBox<>(this.controller.getMovableNames());

    JButton submitDeleteShape = new JButton("Delete Shape");
    submitDeleteShape.addActionListener(e -> {
      try {
        this.controller.removeShape((String) shapeNamesBox.getSelectedItem());
        this.output();
        removeShapeDialog.dispose();
        this.resultBox("Shape successfully deleted! PLease close the dialogue boxes.");
      } catch (IllegalArgumentException | IllegalStateException addShapeException) {
        this.resultBox(
                "Deleting the shape failed. Here is the message: "
                        + addShapeException.getMessage());
      }
    });

    removeShapeDialog.add(deleteShapeLabel);
    removeShapeDialog.add(shapeNamesBox);
    removeShapeDialog.add(submitDeleteShape);
    removeShapeDialog.setVisible(true);
  }

  private void selectShapeBox(JDialog parent) {
    selectShapeDialog = new JDialog(parent, parent.getTitle() + ": Select Shape",
            Dialog.ModalityType.APPLICATION_MODAL);
    selectShapeDialog.setLayout(new BorderLayout());

    JLabel currentSelectedShapeLabel = new JLabel("The current selected shape \nis: '"
            + this.selectedShapeName + "'.");
    JLabel selectShapeLabel = new JLabel("Select Shape: ");
    JComboBox<String> shapeNamesBox = new JComboBox<>(this.controller.getMovableNames());

    JButton submitSelectShape = new JButton("Select Shape");
    submitSelectShape.addActionListener(e -> {
      selectedShapeName = (String) shapeNamesBox.getSelectedItem();
      selectShapeDialog.dispose();
      this.resultBox(
              "The shape, '" + this.selectedShapeName + "', has been selected!"
                      + "You may now exit this pop-up window.");
    });

    JPanel shapesPanel = new JPanel();
    shapesPanel.setLayout(new FlowLayout());
    shapesPanel.add(currentSelectedShapeLabel);
    shapesPanel.add(selectShapeLabel);
    shapesPanel.add(shapeNamesBox);
    selectShapeDialog.add(shapesPanel, BorderLayout.CENTER);
    selectShapeDialog.add(submitSelectShape, BorderLayout.SOUTH);
    selectShapeDialog.setSize(new Dimension(420, 200));
    selectShapeDialog.setLocationRelativeTo(null);
    selectShapeDialog.setVisible(true);
  }

  private void addKeyFrameBox() {
    addKeyFrameDialog = new JDialog(this, "Add Key Frame",
            Dialog.ModalityType.APPLICATION_MODAL);
    // declare the information panel and the editing panel, and add them to the dialog
    addKeyFrameDialog.setLayout(new BorderLayout());
    JPanel informationPanel = new JPanel();
    informationPanel.setLayout(new GridLayout(1, 1));
    addKeyFrameDialog.add(informationPanel, BorderLayout.NORTH);
    JPanel editPanel = new JPanel();
    editPanel.setLayout(new FlowLayout());
    addKeyFrameDialog.add(editPanel, BorderLayout.CENTER);

    this.selectShapeBox(addKeyFrameDialog);

    // create and add the information components to the information panel
    JLabel selectedShapeLabel = new JLabel("The current selected shape \nis: '"
            + this.selectedShapeName + "'.");
    informationPanel.add(selectedShapeLabel);

    // create and add the editing components to the editPanel
    JLabel frameLableTick = new JLabel("Tick: ");
    JLabel frameLabelX = new JLabel("X: ");
    JLabel frameLabelY = new JLabel("Y: ");
    JLabel frameLabelWidth = new JLabel("Width: ");
    JLabel frameLabelHeight = new JLabel("Height: ");
    JLabel frameLabelRed = new JLabel("Red [0, 255]: ");
    JLabel frameLabelGreen = new JLabel("Green [0, 255]: ");
    JLabel frameLabelBlue = new JLabel("Blue [0, 255]: ");
    JTextField frameTick = new JTextField(3);
    JTextField frameX = new JTextField(5);
    JTextField frameY = new JTextField(5);
    JTextField frameWidth = new JTextField(5);
    JTextField frameHeight = new JTextField(5);
    JTextField frameRed = new JTextField(3);
    JTextField frameGreen = new JTextField(3);
    JTextField frameBlue = new JTextField(3);
    JButton submitAddFrame = new JButton("Edit Frame");
    submitAddFrame.addActionListener(e -> {
      try {
        this.controller.removeKeyFrame(this.selectedShapeName, this.selectedKeyFrameIdx);
        this.controller.addKeyFrame(this.selectedShapeName,
                (int) textAsDouble(frameTick.getText()),
                textAsDouble(frameX.getText()), textAsDouble(frameY.getText()),
                textAsDouble(frameWidth.getText()), textAsDouble(frameHeight.getText()),
                (int) textAsDouble(frameRed.getText()), (int) textAsDouble(frameGreen.getText()),
                (int) textAsDouble(frameBlue.getText()));
        this.output();
        addKeyFrameDialog.dispose();
        this.resultBox("Frame successfully added! PLease close the dialogue boxes.");
      } catch (IllegalArgumentException | IllegalStateException addShapeException) {
        this.resultBox(
                "Adding the frame failed. Here is the message: "
                        + addShapeException.getMessage());
      }
    });
    editPanel.add(frameLableTick);
    editPanel.add(frameTick);
    editPanel.add(frameLabelX);
    editPanel.add(frameX);
    editPanel.add(frameLabelY);
    editPanel.add(frameY);
    editPanel.add(frameLabelWidth);
    editPanel.add(frameWidth);
    editPanel.add(frameLabelHeight);
    editPanel.add(frameHeight);
    editPanel.add(frameLabelRed);
    editPanel.add(frameRed);
    editPanel.add(frameLabelGreen);
    editPanel.add(frameGreen);
    editPanel.add(frameLabelBlue);
    editPanel.add(frameBlue);
    editPanel.add(submitAddFrame);

    addKeyFrameDialog.setSize(new Dimension(650, 120));
    addKeyFrameDialog.setLocationRelativeTo(null);
    addKeyFrameDialog.setVisible(true);
  }

  private void selectKeyFrameBox(JDialog parent) {
    JDialog selectKeyFrameDialog = new JDialog(parent, parent.getTitle() + ": Select Key Frame",
            Dialog.ModalityType.APPLICATION_MODAL);
    selectKeyFrameDialog.setLayout(new BorderLayout());

    this.selectShapeBox(parent);

    JLabel selectKeyFrameLabel = new JLabel("Select Key Frame to Edit: ");
    JComboBox<String> keyFramesBox =
            new JComboBox<>(controller.getKeyFrames(this.selectedShapeName));

    JButton submitSelectFrame = new JButton("Select Key Frame");
    submitSelectFrame.addActionListener(e -> {
      try {
        this.selectedKeyFrameStr = (String) keyFramesBox.getSelectedItem();
        this.selectedKeyFrameIdx = keyFramesBox.getSelectedIndex();
        this.resultBox("Frame successfully selected! PLease close the dialogue box.");
        selectKeyFrameDialog.dispose();
      } catch (NullPointerException nPE) {
        this.resultBox(
                "Selecting the frame failed. Here is the message: "
                        + nPE.getMessage());
      }
    });

    JPanel framesPanel = new JPanel();
    framesPanel.setLayout(new FlowLayout());
    framesPanel.add(selectKeyFrameLabel);
    framesPanel.add(keyFramesBox);
    selectKeyFrameDialog.add(framesPanel, BorderLayout.CENTER);
    selectKeyFrameDialog.add(submitSelectFrame, BorderLayout.SOUTH);
    selectKeyFrameDialog.setSize(new Dimension(420, 200));
    selectKeyFrameDialog.setLocationRelativeTo(null);
    selectKeyFrameDialog.setVisible(true);
  }

  private void editKeyFrameBox() {
    JDialog editKeyFrameDialog = new JDialog(this, "Edit Key Frame",
            Dialog.ModalityType.APPLICATION_MODAL);
    // declare the information panel and the editing panel, and add them to the dialog
    editKeyFrameDialog.setLayout(new BorderLayout());
    JPanel informationPanel = new JPanel();
    informationPanel.setLayout(new GridLayout(2, 1));
    editKeyFrameDialog.add(informationPanel, BorderLayout.NORTH);
    JPanel editPanel = new JPanel();
    editPanel.setLayout(new FlowLayout());
    editKeyFrameDialog.add(editPanel, BorderLayout.CENTER);

    this.selectKeyFrameBox(editKeyFrameDialog);

    // create and add the information components to the information panel
    JLabel selectedShapeLabel = new JLabel("The current selected shape \nis: '"
            + this.selectedShapeName + "'.");
    JLabel selectedFrameLabel = new JLabel("The current selected frame \nis: '"
            + this.selectedKeyFrameStr + "'.");
    informationPanel.add(selectedShapeLabel);
    informationPanel.add(selectedFrameLabel);

    // create and add the editing components to the editPanel
    JLabel frameLableTick = new JLabel("Tick: ");
    JLabel frameLabelX = new JLabel("X: ");
    JLabel frameLabelY = new JLabel("Y: ");
    JLabel frameLabelWidth = new JLabel("Width: ");
    JLabel frameLabelHeight = new JLabel("Height: ");
    JLabel frameLabelRed = new JLabel("Red [0, 255]: ");
    JLabel frameLabelGreen = new JLabel("Green [0, 255]: ");
    JLabel frameLabelBlue = new JLabel("Blue [0, 255]: ");
    JTextField frameTick = new JTextField(3);
    JTextField frameX = new JTextField(5);
    JTextField frameY = new JTextField(5);
    JTextField frameWidth = new JTextField(5);
    JTextField frameHeight = new JTextField(5);
    JTextField frameRed = new JTextField(3);
    JTextField frameGreen = new JTextField(3);
    JTextField frameBlue = new JTextField(3);
    JButton submitAddFrame = new JButton("Edit Frame");
    submitAddFrame.addActionListener(e -> {
      try {
        this.controller.removeKeyFrame(this.selectedShapeName, this.selectedKeyFrameIdx);
        this.controller.addKeyFrame(this.selectedShapeName,
                (int) textAsDouble(frameTick.getText()),
                textAsDouble(frameX.getText()), textAsDouble(frameY.getText()),
                textAsDouble(frameWidth.getText()), textAsDouble(frameHeight.getText()),
                (int) textAsDouble(frameRed.getText()), (int) textAsDouble(frameGreen.getText()),
                (int) textAsDouble(frameBlue.getText()));
        this.output();
        editKeyFrameDialog.dispose();
        this.resultBox("Frame successfully added! PLease close the dialogue boxes.");
      } catch (IllegalArgumentException | IllegalStateException addShapeException) {
        this.resultBox(
                "Adding the frame failed. Here is the message: "
                        + addShapeException.getMessage());
      }
    });
    editPanel.add(frameLableTick);
    editPanel.add(frameTick);
    editPanel.add(frameLabelX);
    editPanel.add(frameX);
    editPanel.add(frameLabelY);
    editPanel.add(frameY);
    editPanel.add(frameLabelWidth);
    editPanel.add(frameWidth);
    editPanel.add(frameLabelHeight);
    editPanel.add(frameHeight);
    editPanel.add(frameLabelRed);
    editPanel.add(frameRed);
    editPanel.add(frameLabelGreen);
    editPanel.add(frameGreen);
    editPanel.add(frameLabelBlue);
    editPanel.add(frameBlue);
    editPanel.add(submitAddFrame);

    editKeyFrameDialog.setSize(new Dimension(650, 150));
    editKeyFrameDialog.setLocationRelativeTo(null);
    editKeyFrameDialog.setVisible(true);

  }

  private void removeKeyFrameBox() {
    JDialog removeKeyFrameDialog = new JDialog(this, "Delete Key Frame",
            Dialog.ModalityType.APPLICATION_MODAL);
    removeKeyFrameDialog.setLayout(new FlowLayout());
    removeKeyFrameDialog.setLayout(new FlowLayout());
    removeKeyFrameDialog.setSize(new Dimension(700, 100));
    removeKeyFrameDialog.setLocationRelativeTo(null);

    this.selectShapeBox(removeKeyFrameDialog);

    JLabel deleteKeyFrameLabel = new JLabel("Select Key Frame to Delete: ");
    JComboBox<String> keyFramesBox =
            new JComboBox<>(controller.getKeyFrames(this.selectedShapeName));

    JButton submitDeleteShape = new JButton("Delete Key Frame");
    submitDeleteShape.addActionListener(e -> {
      try {
        this.controller.removeKeyFrame(this.selectedShapeName,
                keyFramesBox.getSelectedIndex());
        this.resultBox("Frame successfully deleted! PLease close the dialogue boxes.");
      } catch (IllegalArgumentException | IllegalStateException addShapeException) {
        this.resultBox(
                "Deleting the frame failed. Here is the message: "
                        + addShapeException.getMessage());
      }
    });

    removeKeyFrameDialog.add(deleteKeyFrameLabel);
    removeKeyFrameDialog.add(keyFramesBox);
    removeKeyFrameDialog.add(submitDeleteShape);

    removeKeyFrameDialog.setVisible(true);
  }

  // parses the text for a double, and returns 0 upon failure
  private double textAsDouble(String text) {
    try {
      return Double.parseDouble(text);
    } catch (NumberFormatException | NullPointerException e) {
      return 0;
    }
  }

  private void resultBox(String resultMessage) {
    JDialog dialog = new JDialog(this, "Action Result",
            Dialog.ModalityType.APPLICATION_MODAL);
    dialog.setLayout(new FlowLayout());
    dialog.setSize(new Dimension(700, 100));
    dialog.setLocationRelativeTo(null);

    JLabel label = new JLabel(resultMessage);

    dialog.add(label);

    dialog.setVisible(true);
  }

}
